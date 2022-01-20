package cn.t.util.common.mail;

public class SenderConfig {

    /**
     * 邮件服务器
     */
    private String host;

    /**
     * 是否需要验证
     */
    private boolean needAuth = true;

    /**
     * 是否启用ssl
     */
    private boolean enableSsl = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }

    public boolean isEnableSsl() {
        return enableSsl;
    }

    public void setEnableSsl(boolean enableSsl) {
        this.enableSsl = enableSsl;
    }
}
