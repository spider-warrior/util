package cn.t.util.nb.entity;

public class DeviceActivationStatus {

    private String deviceId;
    private Boolean activated;
    private String name;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceActivationStatus{" +
            "deviceId='" + deviceId + '\'' +
            ", activated=" + activated +
            ", name='" + name + '\'' +
            '}';
    }
}
