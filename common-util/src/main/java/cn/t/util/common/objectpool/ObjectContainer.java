package cn.t.util.common.objectpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-04-25 21:34
 **/
public class ObjectContainer {
    private static final Map<Class<?>, Supplier<?>> providerMap = new ConcurrentHashMap<>();

    public static void config(List<ObjectConfig<?>> configList) {
        configList.forEach(config -> {
            providerMap.put(config.getClazz(), config.getSupplier());
        });
    }

    public static class ObjectConfig<T> {
        private Class<T> clazz;
        private Supplier<T> supplier;
        private int max;

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
    }

    public static class Pool {
        private final int max;
        private final List<Object> list;

        public Pool(int max) {
            this.max = max;
            this.list = new ArrayList<>();
        }

    }

    public static class ObjectUnit<T> {
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public void release() {

        }
    }
}
