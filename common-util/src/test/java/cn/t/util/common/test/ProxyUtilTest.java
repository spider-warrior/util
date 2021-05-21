package cn.t.util.common.test;

import cn.t.util.common.reflect.ProxyUtil;
import cn.t.util.common.proxy.ProxyCallback;
import cn.t.util.common.proxy.ProxyConfig;
import cn.t.util.common.test.entity.Animal;
import cn.t.util.common.test.entity.Dog;
import cn.t.util.common.test.entity.SubClass;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class ProxyUtilTest {

    private ProxyCallback callback;
    private ProxyConfig config;

    @Before
    public void init() {
        callback = new ProxyCallback() {
            @Override
            public void before(Object obj, Method method, Object[] args) {
                System.out.println("before....");
            }

            @Override
            public void after(Object result) {
                System.out.println("after....");
            }
        };
        config = new ProxyConfig();
        config.setProxyCallback(callback);
        config.setInterceptMethods(new String[]{"shout", "eat"});
    }

    @Test
    public void testCglibProxy() {
        Dog dog = new Dog();
        Dog dogProxy = (Dog) ProxyUtil.generateCglibProxy(dog, config);
        dogProxy.shout();
    }

    @Test
    public void testJdkProxy() {
        Dog dog = new Dog();
        Animal dogProxy = (Animal) ProxyUtil.generateJdkProxy(dog, config);
        dogProxy.shout();
    }

    @Test
    public void findTypeParamTest() {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getClazz());
    }
}



