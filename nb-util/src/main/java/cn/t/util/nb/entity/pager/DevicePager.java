package cn.t.util.nb.entity.pager;

import cn.t.util.nb.entity.Device;

import java.util.List;

public class DevicePager extends Pager {

    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "DevicePager{" +
            "devices=" + devices +
            "} " + super.toString();
    }
}
