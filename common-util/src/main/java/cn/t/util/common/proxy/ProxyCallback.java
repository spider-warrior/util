package cn.t.util.common.proxy;

import java.lang.reflect.Method;

/**
 * 代理类回调
 */
public interface ProxyCallback {

    /**
     * before invoke
     * @param obj    xxx
     * @param method xxx
     * @param args xxx
     */
    void before(Object obj, Method method, Object[] args);

    /**
     * after invoke
     * @param result xxx
     */
    void after(Object result);

}
