package cn.t.util.nb.entity.notify;

import cn.t.util.nb.entity.DeviceInfo;

public class ModifyDeviceNotifyMessage extends BaseNotifyMessage {

    private DeviceInfo deviceInfo;

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public String toString() {
        return "ModifyDeviceNotifyMessage{" +
            "deviceInfo=" + deviceInfo +
            "} " + super.toString();
    }
}
