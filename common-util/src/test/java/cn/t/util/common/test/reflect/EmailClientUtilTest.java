//package cn.t.util.common.test.reflect;
//
//import cn.t.util.common.mail.EmailClientUtil;
//import cn.t.util.common.mail.EmailMessage;
//import cn.t.util.common.mail.SenderConfig;
//import cn.t.util.common.MailHost;
//import org.junit.Test;
//
//import javax.mail.internet.InternetAddress;
//
//public class EmailClientUtilTest {
//    @Test
//    public void testSendQQEmail() {
//        try {
//            String username = "362961910@qq.com";
//            String password = "xxxxxxxxx";
//            EmailMessage message = new EmailMessage();
//            message.setTarget(new InternetAddress("qq362961910@126.com"));
//            message.setSubject("test e-mail");
//            message.setContent("<h1>this is a test email</h1");
//            SenderConfig config = new SenderConfig();
//            config.setEnableSsl(true);
//            config.setHost(MailHost.QQ.getSmtp());
//            config.setNeedAuth(true);
//            EmailClientUtil.sendEmail(username, password, message, config);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testSend126Email() {
//        try {
//            String username = "qq362961910@126.com";
//            String password = "xxxxxxxxx";
//            EmailMessage message = new EmailMessage();
//            message.setTarget(new InternetAddress("362961910@qq.com"));
//            message.setSubject("test e-mail");
//            message.setContent("<h1>this is a test email</h1");
//            SenderConfig config = new SenderConfig();
//            config.setEnableSsl(true);
//            config.setHost(MailHost.NetEase126.getSmtp());
//            config.setNeedAuth(true);
//            EmailClientUtil.sendEmail(username, password, message, config);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testSendLibyEmail() {
//        try {
//            String username = "yangjian@liby.ltd";
//            String password = "godfather123";
//            EmailMessage message = new EmailMessage();
//            message.setTarget(new InternetAddress("362961910@qq.com"));
//            message.setSubject("test e-mail");
//            message.setContent("<h1>this is a test email</h1");
//            SenderConfig config = new SenderConfig();
//            config.setEnableSsl(true);
//            config.setHost(MailHost.Ali.getSmtp());
//            config.setNeedAuth(true);
//            EmailClientUtil.sendEmail(username, password, message, config);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
