package cn.t.util.common.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailClientUtil.class);

    public static void sendEmail(String username, String password, EmailMessage emailMessage, SenderConfig config) throws GeneralSecurityException {
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", config.getHost());
        properties.setProperty("mail.smtp.auth", String.valueOf(config.isNeedAuth()));
        //是否启用ssl
        if (config.isEnableSsl()) {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
        }
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(username));
            // Set To: 头部头字段
            message.addRecipients(Message.RecipientType.TO, emailMessage.getTarget());
            // Set Subject: 头部头字段
            message.setSubject(emailMessage.getSubject());
            // 设置消息体
            message.setText(emailMessage.getContent());
            // 发送消息
            Transport.send(message);
            logger.debug("send message successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
