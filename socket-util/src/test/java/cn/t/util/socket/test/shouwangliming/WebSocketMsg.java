package cn.t.util.socket.test.shouwangliming;

import java.util.Map;

public class WebSocketMsg {
    private String cmd;
    private Long sendTime = System.currentTimeMillis() - 5000;
    private Map<String, Object> params;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public WebSocketMsg(String cmd, Map<String, Object> params) {
        this.cmd = cmd;
        this.params = params;
    }
}
