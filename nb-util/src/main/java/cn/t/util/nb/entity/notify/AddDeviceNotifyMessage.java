package cn.t.util.nb.entity.notify;

import cn.t.util.nb.entity.DeviceInfo;

public class AddDeviceNotifyMessage extends BaseNotifyMessage {
    private String nodeType;
    private DeviceInfo deviceInfo;

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public String toString() {
        return "AddDeviceNotifyMessage{" +
            "nodeType='" + nodeType + '\'' +
            ", deviceInfo=" + deviceInfo +
            "} " + super.toString();
    }
}
