package cn.t.util.nb.entity.pager;

import cn.t.util.nb.entity.DeviceDataHistory;

import java.util.List;

public class DeviceDataHistoryPager extends Pager {

    private List<DeviceDataHistory> deviceDataHistoryDTOs;

    public List<DeviceDataHistory> getDeviceDataHistoryDTOs() {
        return deviceDataHistoryDTOs;
    }

    public void setDeviceDataHistoryDTOs(List<DeviceDataHistory> deviceDataHistoryDTOs) {
        this.deviceDataHistoryDTOs = deviceDataHistoryDTOs;
    }

    @Override
    public String toString() {
        return "DeviceDataHistoryPager{" +
            "deviceDataHistoryDTOs=" + deviceDataHistoryDTOs +
            "} " + super.toString();
    }
}
