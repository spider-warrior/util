package cn.t.util.common;

public enum MailHost {

    /**
     * 腾讯
     */
    QQ("pop.qq.com", "imap.qq.com", "smtp.qq.com", "dav.qq.com", "dav.qq.com"),

    /**
     * 126
     */
    NetEase126("pop.126.com", "imap.126.com", "smtp.126.com", "dav.126.com", "dav.126.com"),

    /**
     * 163
     */
    NetEase1163("pop.163.com", "imap.163.com", "smtp.163.com", "dav.163.com", "dav.163.com"),


    /**
     * Ali
     */
    Ali("pop.mxhichina.com", "imap.mxhichina.com", "smtp.mxhichina.com", "dav.mxhichina.com", "dav.mxhichina.com")

    ;


    private String pop3;

    private String imap;

    private String smtp;

    private String cardDav;

    private String calDav;

    MailHost(String pop3, String imap, String smtp, String cardDav, String calDav) {
        this.pop3 = pop3;
        this.imap = imap;
        this.smtp = smtp;
        this.cardDav = cardDav;
        this.calDav = calDav;
    }

    public String getPop3() {
        return pop3;
    }

    public void setPop3(String pop3) {
        this.pop3 = pop3;
    }

    public String getImap() {
        return imap;
    }

    public void setImap(String imap) {
        this.imap = imap;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getCardDav() {
        return cardDav;
    }

    public void setCardDav(String cardDav) {
        this.cardDav = cardDav;
    }

    public String getCalDav() {
        return calDav;
    }

    public void setCalDav(String calDav) {
        this.calDav = calDav;
    }
}
