package cn.t.util.nb.entity;

import java.util.List;

public class CancelDeviceTaskResponse {

    private String taskId;
    private String appId;
    private String deviceId;
    private String status;
    private Integer totalCount;
    private List<String> deviceCommands;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<String> getDeviceCommands() {
        return deviceCommands;
    }

    public void setDeviceCommands(List<String> deviceCommands) {
        this.deviceCommands = deviceCommands;
    }

    @Override
    public String toString() {
        return "CancelDeviceTaskResponse{" +
            "taskId='" + taskId + '\'' +
            ", appId='" + appId + '\'' +
            ", deviceId='" + deviceId + '\'' +
            ", status='" + status + '\'' +
            ", totalCount=" + totalCount +
            ", deviceCommands=" + deviceCommands +
            '}';
    }
}
