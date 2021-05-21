package cn.t.util.nb.entity;

import java.util.Map;

public class DeviceDataHistory {

    private String deviceId;
    private String gatewayId;
    private String appId;
    private String serviceId;
    private Map<String, Object> data;
    private String timestamp;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DeviceDataHistory{" +
            "deviceId='" + deviceId + '\'' +
            ", gatewayId='" + gatewayId + '\'' +
            ", appId='" + appId + '\'' +
            ", serviceId='" + serviceId + '\'' +
            ", data=" + data +
            ", timestamp='" + timestamp + '\'' +
            '}';
    }
}
