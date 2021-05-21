package cn.t.util.nb.entity;

public class AsyncCommandV4 {
    private String commandId;
    private String appId;
    private String deviceId;
    private AsyncCommand command;
    private String callbackUrl;
    private Integer expireTime;
    private String status;
    private String creationTime;
    private String issuedTimes;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
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

    public AsyncCommand getCommand() {
        return command;
    }

    public void setCommand(AsyncCommand command) {
        this.command = command;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getIssuedTimes() {
        return issuedTimes;
    }

    public void setIssuedTimes(String issuedTimes) {
        this.issuedTimes = issuedTimes;
    }

    @Override
    public String toString() {
        return "AsyncCommandV4{" +
            "commandId='" + commandId + '\'' +
            ", appId='" + appId + '\'' +
            ", deviceId='" + deviceId + '\'' +
            ", command=" + command +
            ", callbackUrl='" + callbackUrl + '\'' +
            ", expireTime=" + expireTime +
            ", status='" + status + '\'' +
            ", creationTime='" + creationTime + '\'' +
            ", issuedTimes='" + issuedTimes + '\'' +
            '}';
    }
}
