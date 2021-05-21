package cn.t.util.nb.entity;

import java.util.Map;

public class DeviceService {

    private String serviceId;
    private String serviceType;
    private Map<String, Object> data;
    private String eventTime;
    private ServiceInfo serviceInfo;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    @Override
    public String toString() {
        return "DeviceService{" +
            "serviceId='" + serviceId + '\'' +
            ", serviceType='" + serviceType + '\'' +
            ", data=" + data +
            ", eventTime='" + eventTime + '\'' +
            ", serviceInfo=" + serviceInfo +
            '}';
    }
}
