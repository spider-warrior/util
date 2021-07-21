package cn.t.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 对象池
 *
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-04-25 21:34
 **/
public class ObjectPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectPoolUtil.class);
    private static final Map<Class<?>, PoolConfig<?>> providerMap = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Pool<?>> poolMap =  new ConcurrentHashMap<>();

    public static void config(List<PoolConfig<?>> configList) {
        configList.forEach(config -> providerMap.put(config.getClazz(), config));
    }

    @SuppressWarnings("unchecked")
    public static <T> ObjectUnit<T> getInstance(Class<T> clazz) {
        Pool<T> pool = (Pool<T>)poolMap.get(clazz);
        if(pool == null) {
            //create pool by config
            PoolConfig<T> config = (PoolConfig<T>)providerMap.get(clazz);
            if(config == null) {
                throw new RuntimeException("class config not exist: " + clazz);
            }
            Supplier<T> supplier = config.getSupplier();
            //init pool
            pool = new Pool<>();
            pool.setName(clazz.getSimpleName());
            pool.setMax(config.getMax());
            pool.setIdle(config.getIdle());
            pool.setTtl(config.getTtl());
            pool.setSupplier(supplier);
            poolMap.put(clazz, pool);
        }
        return pool.getInstance();
    }

    static {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "object-pool");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(ObjectPoolUtil::checkPool, 1, 1, TimeUnit.SECONDS);
    }

    private static void checkPool() {
        poolMap.forEach((clazz, pool) -> {
            pool.clearExpireUnit();
        });
    }

    public static class PoolConfig<T> {
        private Class<T> clazz;
        private Supplier<T> supplier;
        private int max;
        private int min;
        private int idle;
        private long ttl;

        public Class<T> getClazz() {
            return clazz;
        }

        public void setClazz(Class<T> clazz) {
            this.clazz = clazz;
        }

        public Supplier<T> getSupplier() {
            return supplier;
        }

        public void setSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getIdle() {
            return idle;
        }

        public void setIdle(int idle) {
            this.idle = idle;
        }

        public long getTtl() {
            return ttl;
        }

        public void setTtl(long ttl) {
            this.ttl = ttl;
        }
    }

    private static class Pool<T> {
        private String name;
        private int max = 10;
        private int min = 2;
        private int idle = 2;
        private long ttl = 10000;
        private Supplier<T> supplier;
        private final List<ObjectUnit<T>> inUseList = new ArrayList<>();
        private final List<ObjectUnit<T>> leisureList = new ArrayList<>();

        private final RejectionHandler rejectionHandler = () -> {
            throw new RuntimeException(name + " is full, current size: " + inUseList.size() +", max: " + max + ", min: " + min  + ", idle: " + idle + ", ttl: " + ttl);
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getIdle() {
            return idle;
        }

        public void setIdle(int idle) {
            this.idle = idle;
        }

        public long getTtl() {
            return ttl;
        }

        public void setTtl(long ttl) {
            this.ttl = ttl;
        }

        public Supplier<T> getSupplier() {
            return supplier;
        }

        public void setSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public List<ObjectUnit<T>> getInUseList() {
            return inUseList;
        }

        public List<ObjectUnit<T>> getLeisureList() {
            return leisureList;
        }

        public synchronized ObjectUnit<T> getInstance() {
            ObjectUnit<T> unit;
            if(leisureList.size() > 0) {
                //空闲队列中取出一个元素
                unit = leisureList.remove(0);
            } else {
                //达到最大数量
                if(inUseList.size() > max) {
                    rejectionHandler.reject();
                }
                T t = supplier.get();
                unit = new ObjectUnit<>(t, this);
            }
            //使用队列中添加一个元素
            inUseList.add(unit);
            //设置当前线程为拥有者
            unit.acquire();
            return unit;
        }

        public synchronized boolean release(ObjectUnit<T> t) {
            boolean success = inUseList.remove(t);
            if(!success) {
                logger.warn("release object is not pooled");
                return false;
            } else {
                leisureList.add(t);
                return true;
            }
        }

        public synchronized void clearExpireUnit() {
            logger.info("[before] pool-{} check, leisure size: {}, inUseSize: {}, max: {}, min: {}, idle: {}, ttl: {}",
                this.name, this.leisureList.size(), this.inUseList.size(), this.max, this.min, this.idle, this.ttl);
            //没有空闲对象
            if(leisureList.size() == 0) {
                return;
            }
            //总数量小于min
            if(leisureList.size() + inUseList.size() < min) {
                return;
            }
            LinkedList<ObjectUnit<T>> expiredUnitList = new LinkedList<>();
            long now = SystemUtil.now();
            leisureList.forEach(unit -> {
                if(now - unit.getUpdateTime() > ttl) {
                    expiredUnitList.add(unit);
                }
            });
            int remain = leisureList.size() + inUseList.size() - expiredUnitList.size();
            int extra = remain - idle;
            if(extra < 0) {
                while ((extra++) < 0) {
                    expiredUnitList.poll();
                }
            }
            leisureList.removeAll(expiredUnitList);
            logger.info("[after] pool-{} check, leisure size: {}, inUseSize: {}, max: {}, min: {}, idle: {}, ttl: {}",
                this.name, this.leisureList.size(), this.inUseList.size(), this.max, this.min, this.idle, this.ttl);
        }
    }

    public static class ObjectUnit<T> {
        private final ThreadLocal<T> tl = new ThreadLocal<>();
        private final Pool<T> pool;
        private boolean inUse = false;
        private final T t;
        private long updateTime;

        private ObjectUnit(T t, Pool<T> pool) {
            this.t = t;
            this.pool = pool;
            updateTime = System.currentTimeMillis();
        }

        public T getT() {
            return t;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        private void acquire() {
            //如果被使用中
            if(inUse) {
                //使用者非当前线程
                T t = tl.get();
                if(t == null) {
                    throw new RuntimeException("object already use by another thread!");
                }
            } else {
                //设置对象拥有线程
                tl.set(t);
                inUse = true;
            }
            updateTime = System.currentTimeMillis();
        }

        public boolean release() {
            T t = tl.get();
            if(t == null) {
                logger.warn("object is already released by this thread or object is not belong to current thread");
                return false;
            }
            tl.remove();
            inUse = false;
            updateTime = System.currentTimeMillis();
            return pool.release(this);
        }
    }

    @FunctionalInterface
    interface RejectionHandler {
        void reject();
    }

}