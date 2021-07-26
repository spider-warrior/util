package cn.t.util.common.reflect;

import cn.t.util.common.proxy.ProxyCallback;
import cn.t.util.common.proxy.ProxyConfig;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * JDK与Cglib区别
 * 1.Jdk需要声明接口 Cglib不需要生成接口
 * 2.Jdk代理实力只能用接口类型赋值, Cglib没有此限制
 * 3.在Jdk代理类中调用另外一个方法时不会再进切面, 而在Cglib类中调用另外一个方法依然会进切面
 * <p>
 * 总结: Cglib更方便使用
 */
public class ProxyUtil {

    public static Object generateCglibProxy(Object target, ProxyConfig config) {
        CglibProxy proxy = new CglibProxy(target, config.getProxyCallback());
        CglibProxyCallbackFilter filter = new CglibProxyCallbackFilter(proxy, config);
        Enhancer enhancer = new Enhancer();
        //设置要被代理的类
        enhancer.setSuperclass(target.getClass());
        //设置多个拦截器(setCallbacks中的拦截器顺序一定要和callbackFilter中的顺序一致)
        enhancer.setCallbacks(new Callback[]{proxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(filter);
        return enhancer.create();
    }

    public static Object generateJdkProxy(Object target, ProxyConfig config) {
        JdkProxy jdkProxy = new JdkProxy(target, config);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), jdkProxy);
    }

    /**
     * 代理
     */
    private interface BaseProxy {
        default boolean apply(Method method, String... methods) {
            if (methods != null && methods.length != 0) {
                String methodName = method.getName();
                for (String m : methods) {
                    if ("*".equals(methodName) || methodName.equals(m)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private static class CglibProxyCallbackFilter implements CallbackFilter {

        private final BaseProxy proxy;
        private final ProxyConfig config;

        private CglibProxyCallbackFilter(BaseProxy proxy, ProxyConfig config) {
            this.proxy = proxy;
            this.config = config;
        }

        @Override
        public int accept(Method method) {
            return proxy.apply(method, config.getInterceptMethods()) ? 0 : 1;
        }
    }

    /**
     * cglib代理
     */
    private static class CglibProxy implements MethodInterceptor, BaseProxy {

        private final Object target;

        private final ProxyCallback callback;

        private CglibProxy(Object target, ProxyCallback callback) {
            this.target = target;
            this.callback = callback;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            callback.before(target, method, args);
            Object result = method.invoke(target, args);
            callback.after(result);
            return result;
        }
    }

    /**
     * jdk代理
     */
    private static class JdkProxy implements java.lang.reflect.InvocationHandler, BaseProxy {

        private final Object target;

        private final ProxyConfig config;

        private JdkProxy(Object target, ProxyConfig config) {
            this.target = target;
            this.config = config;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean apply = apply(method, config.getInterceptMethods());
            if (apply) {
                config.getProxyCallback().before(target, method, args);
            }
            Object result = method.invoke(target, args);
            if (apply) {
                config.getProxyCallback().after(result);
            }
            return result;
        }
    }

}
