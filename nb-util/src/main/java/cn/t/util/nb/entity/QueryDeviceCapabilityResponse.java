package cn.t.util.nb.entity;

import java.util.List;

public class QueryDeviceCapabilityResponse {

    private List<DeviceCapability> deviceCapabilities;

    public List<DeviceCapability> getDeviceCapabilities() {
        return deviceCapabilities;
    }

    public void setDeviceCapabilities(List<DeviceCapability> deviceCapabilities) {
        this.deviceCapabilities = deviceCapabilities;
    }

    @Override
    public String toString() {
        return "QueryDeviceCapabilityResponse{" +
            "deviceCapabilities=" + deviceCapabilities +
            '}';
    }
}
