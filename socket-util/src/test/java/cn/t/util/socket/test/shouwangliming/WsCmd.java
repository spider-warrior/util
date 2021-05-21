package cn.t.util.socket.test.shouwangliming;

import java.util.Map;

public class WsCmd {
    private Long pushtime;
    private Long clientid;
    private String cmd;
    private Long networkOptimizationTimeout;
    private String pushseq;
    private Boolean enableNetworkOptimization;
    private Long serverTime;
    private Long networkOptimizationTestDelay;
    private String server;
    private Map<String, Object> data;

    public Long getPushtime() {
        return pushtime;
    }

    public void setPushtime(Long pushtime) {
        this.pushtime = pushtime;
    }

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Long getNetworkOptimizationTimeout() {
        return networkOptimizationTimeout;
    }

    public void setNetworkOptimizationTimeout(Long networkOptimizationTimeout) {
        this.networkOptimizationTimeout = networkOptimizationTimeout;
    }

    public String getPushseq() {
        return pushseq;
    }

    public void setPushseq(String pushseq) {
        this.pushseq = pushseq;
    }

    public Boolean getEnableNetworkOptimization() {
        return enableNetworkOptimization;
    }

    public void setEnableNetworkOptimization(Boolean enableNetworkOptimization) {
        this.enableNetworkOptimization = enableNetworkOptimization;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public Long getNetworkOptimizationTestDelay() {
        return networkOptimizationTestDelay;
    }

    public void setNetworkOptimizationTestDelay(Long networkOptimizationTestDelay) {
        this.networkOptimizationTestDelay = networkOptimizationTestDelay;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
