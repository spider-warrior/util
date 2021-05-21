package cn.t.util.nb.entity.notify;

import cn.t.util.nb.entity.DeviceService;

public class DeviceDataChangedNotifyMessage {

    private String requestId;
    private DeviceService service;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public DeviceService getService() {
        return service;
    }

    public void setService(DeviceService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "DeviceDataChangedNotifyMessage{" +
            "requestId='" + requestId + '\'' +
            ", service=" + service +
            '}';
    }
}
