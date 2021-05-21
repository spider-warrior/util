package cn.t.util.nb.entity;

import java.util.Map;

public class AsyncCommand {
    private String serviceId;
    private String method;
    private Map<String, Object> paras;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParas() {
        return paras;
    }

    public void setParas(Map<String, Object> paras) {
        this.paras = paras;
    }

    @Override
    public String toString() {
        return "AsyncCommand{" +
            "serviceId='" + serviceId + '\'' +
            ", method='" + method + '\'' +
            ", paras=" + paras +
            '}';
    }
}
