package cn.t.util.nb.entity;

import java.util.List;

public class DeviceCapability {

    private String deviceId;
    private List<ServiceCapability> serviceCapabilities;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<ServiceCapability> getServiceCapabilities() {
        return serviceCapabilities;
    }

    public void setServiceCapabilities(List<ServiceCapability> serviceCapabilities) {
        this.serviceCapabilities = serviceCapabilities;
    }

    @Override
    public String toString() {
        return "DeviceCapability{" +
            "deviceId='" + deviceId + '\'' +
            ", serviceCapabilities=" + serviceCapabilities +
            '}';
    }
}
