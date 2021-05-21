package cn.t.util.common.proxy;

public class ProxyConfig {

    /**
     * 需要代理的
     */
    private String[] interceptMethods;

    /**
     * 切面逻辑
     */
    private ProxyCallback proxyCallback;

    public String[] getInterceptMethods() {
        return interceptMethods;
    }

    public void setInterceptMethods(String[] interceptMethods) {
        this.interceptMethods = interceptMethods;
    }

    public ProxyCallback getProxyCallback() {
        return proxyCallback;
    }

    public void setProxyCallback(ProxyCallback proxyCallback) {
        this.proxyCallback = proxyCallback;
    }
}
