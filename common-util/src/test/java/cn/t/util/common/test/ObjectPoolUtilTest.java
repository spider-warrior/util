package cn.t.util.common.test;

import cn.t.util.common.ObjectPoolUtil;
import cn.t.util.common.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-20 20:56
 **/
public class ObjectPoolUtilTest {

    public static void main(String[] args) {
        initPoolConfig();
        for(int i=0; i<1000; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.submit(() -> {
                if(System.currentTimeMillis() % 2 == 0) {
                    ObjectPoolUtil.ObjectUnit<Dog> unit = ObjectPoolUtil.getInstance(Dog.class);
                    Dog dog = unit.getT();
                    dog.shout();
                    unit.release();
                } else {
                    ObjectPoolUtil.ObjectUnit<Cat> unit = ObjectPoolUtil.getInstance(Cat.class);
                    Cat cat = unit.getT();
                    cat.shout();
                    unit.release();
                }
            });
//            LockSupport.parkNanos(100000000);
//            new Thread(() -> {
//                if(System.currentTimeMillis() % 2 == 0) {
//                    ObjectPoolUtil.ObjectUnit<Dog> unit = ObjectPoolUtil.getInstance(Dog.class);
//                    Dog dog = unit.getT();
//                    dog.shout();
//                    unit.release();
//                } else {
//                    ObjectPoolUtil.ObjectUnit<Cat> unit = ObjectPoolUtil.getInstance(Cat.class);
//                    Cat cat = unit.getT();
//                    cat.shout();
//                    unit.release();
//                }
//            }).start();
        }
    }


    private static void initPoolConfig() {
        List<ObjectPoolUtil.PoolConfig<?>> configList = new ArrayList<>();
        ObjectPoolUtil.PoolConfig<Dog> dogPoolConfig = new ObjectPoolUtil.PoolConfig<>();
        dogPoolConfig.setClazz(Dog.class);
        dogPoolConfig.setMax(10);
        dogPoolConfig.setMin(1);
        dogPoolConfig.setTtl(5000);
        dogPoolConfig.setSupplier(new DogSupplier());
        configList.add(dogPoolConfig);

        ObjectPoolUtil.PoolConfig<Cat> catPoolConfig = new ObjectPoolUtil.PoolConfig<>();
        catPoolConfig.setClazz(Cat.class);
        catPoolConfig.setMax(20);
        catPoolConfig.setMin(1);
        catPoolConfig.setTtl(5000);
        catPoolConfig.setSupplier(new CatSupplier());
        configList.add(catPoolConfig);

        ObjectPoolUtil.config(configList);
    }
}

interface Animal {
    void shout();
}

class DogSupplier implements Supplier<Dog> {
    private final AtomicInteger id = new AtomicInteger(1);
    @Override
    public Dog get() {
        return new Dog(id.getAndIncrement());
    }
}

class CatSupplier implements Supplier<Cat> {
    private final AtomicInteger id = new AtomicInteger(1);
    @Override
    public Cat get() {
        return new Cat(id.getAndIncrement());
    }
}

class Dog implements Animal {
    private final int id;
    @Override
    public void shout() {
        LockSupport.parkNanos(RandomUtil.randomInt(1000000000, 2000000000));
        System.out.println(id + ": wang wang");
    }

    public Dog(int id) {
        this.id = id;
    }
}

class Cat implements Animal {
    private final int id;
    @Override
    public void shout() {
        LockSupport.parkNanos(RandomUtil.randomInt(1000000000, 2000000000));
        System.out.println(id + ": miao miao");
    }

    public Cat(int id) {
        this.id = id;
    }
}
