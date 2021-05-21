package cn.t.util.nb.entity;

public class RegisterDeviceResponse {

    private String deviceId;
    private String verifyCode;
    private Integer timeout;
    private String psk;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getPsk() {
        return psk;
    }

    public void setPsk(String psk) {
        this.psk = psk;
    }

    @Override
    public String toString() {
        return "RegisterDeviceResponse{" +
            "deviceId='" + deviceId + '\'' +
            ", verifyCode='" + verifyCode + '\'' +
            ", timeout=" + timeout +
            ", psk='" + psk + '\'' +
            '}';
    }
}
