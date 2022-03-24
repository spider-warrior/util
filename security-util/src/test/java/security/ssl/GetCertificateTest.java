package security.ssl;

import cn.t.util.common.DateUtil;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2022-03-24 10:33
 **/
public class GetCertificateTest {
    public static void main(String[] args) throws Exception {
//        URL httpsURL = new URL("https://www.baidu.com/");
        URL httpsURL = new URL("https://ifenxi.net/");
//        URL httpsURL = new URL("https://www.chiina315.com/");
        //validate
        HttpsURLConnection connection = (HttpsURLConnection) httpsURL.openConnection();
        connection.connect();
        Certificate[] certs = connection.getServerCertificates();
        Certificate certificate = certs[0];
        if(certificate instanceof X509Certificate) {
            X509Certificate x509Certificate = (X509Certificate)certificate;
            System.out.println("版本号:"+ x509Certificate.getVersion());
            System.out.println("序列号:"+  x509Certificate.getSerialNumber().toString(16));
            System.out.println("签发者："+ x509Certificate.getIssuerDN());
            System.out.println("有效起始日期："+ DateUtil.convertToDateTimeString(x509Certificate.getNotBefore()));
            System.out.println("有效终止日期："+ DateUtil.convertToDateTimeString(x509Certificate.getNotAfter()));
            System.out.println("主体名："+ x509Certificate.getSubjectDN());
            System.out.println("签名算法："+ x509Certificate.getSigAlgName());
            System.out.println("签名："+ Arrays.toString(x509Certificate.getSignature()));
            boolean trusted;
            try {
                x509Certificate.verify(certs[1].getPublicKey());
                trusted = true;
            } catch (Exception ignore) {
                trusted = false;
            }
            System.out.println("信任状态: " + trusted);
        } else {
            System.out.println(certificate.getPublicKey());
        }
    }
}
