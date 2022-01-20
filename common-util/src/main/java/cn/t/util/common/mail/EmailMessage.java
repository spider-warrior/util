package cn.t.util.common.mail;

import javax.mail.internet.InternetAddress;

public class EmailMessage {

    private InternetAddress target[];

    private String subject;

    private String content;

    public InternetAddress[] getTarget() {
        return target;
    }

    public void setTarget(InternetAddress... target) {
        this.target = target;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
