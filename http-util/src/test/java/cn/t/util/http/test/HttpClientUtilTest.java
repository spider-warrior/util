package cn.t.util.http.test;

import cn.t.util.common.ArrayUtil;
import cn.t.util.http.*;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class HttpClientUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);

    @Test
    public void getTest() throws IOException {
        System.out.printf("异常: %s%nurl: www.baidu.com", "123");
//        String url = "http://127.0.0.1:8080/test";
//        Map<String, String> headers = new HashMap<>();
//        headers.put("my-header", "this is my header");
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", "amen");
//        HttpResponseEntity responseEntity = HttpClientUtil.get(url, headers, params);
//        logger.info("status: {}", responseEntity.getCode());
//        logger.info("content type: {}", responseEntity.getHeader(HttpHeaders.CONTENT_TYPE));
//        logger.info("content: {}", responseEntity.getContent());
    }

    @Test
    public void postTest() throws IOException {
        String url = "http://127.0.0.1:8080/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("xxx", "1111");
        Map<String, String> params = new HashMap<>();
        params.put("name", "amen");
        HttpResponseEntity responseEntity = HttpClientUtil.post(url, headers, params, ParamFormat.URL_FORM_ENCODE);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getHeader(HttpHeaders.CONTENT_TYPE));
        logger.info("content: {}", responseEntity.getContent());

        logger.info("-------------------------------------------------------------------");

        url = "http://127.0.0.1:8080/test/json_post";
        responseEntity = HttpClientUtil.post(url, headers, params, ParamFormat.APPLICATION_JSON);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getHeader(HttpHeaders.CONTENT_TYPE));
        logger.info("content: {}", responseEntity.getContent());
    }

    @Test
    public void encodeToParamStringTest() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("loginId", "362961910");
        payload.put("passwd", "123456");
        payload.put("pwd", "123456");
        payload.put("authCode", "a1b2c3d4");
        payload.put("auto", false);
        payload.put("into", 0);

        String paramStr = HttpClientUtil.encodeToParamString(payload, false);
        System.out.println(paramStr);
    }

    @Test
    public void specifyCertifyKeyTest() {
        String uri = "https://180.101.147.89:8743/iocm/app/sec/v1.1.0/login";
        Map<String, Object> params = new HashMap<>();
        params.put("appId", "1111");
        params.put("secret", "2222");
        try {
            KeyStore keyStore = KeyStoreUtil.loadCertificate(CertificateType.PKCS12, "", "".toCharArray());
            KeyManagerFactory keyManagerFactory = KeyStoreUtil.initSunX509KeyManagerFactory(keyStore, "".toCharArray());
            KeyStore trustStore = KeyStoreUtil.loadCertificate(CertificateType.JKS, "", "".toCharArray());
            TrustManagerFactory trustManagerFactory = KeyStoreUtil.initSunX509TrustManagerFactory(trustStore);
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(uri, params, ParamFormat.URL_FORM_ENCODE, keyManagerFactory, trustManagerFactory);
            logger.info("result: {}", entity);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void selfCertificateGetTest() throws Exception {
        String uri = "http://liby.ltd/";
//        String uri = "https://liby.ltd/";
//        String uri = "https://www.baidu.com";
        HttpResponseEntity entity = HttpClientUtil.get(uri, Collections.emptyMap());
        System.out.println(entity);
    }
    @Test
    public void selfCertificateSslGetWithoutCertificateCheckTest() throws Exception {
        String uri = "https://liby.com/";
        HttpResponseEntity entity = HttpClientUtil.sslGetInSecure(uri, Collections.emptyMap());
        System.out.println(entity);
    }

    @Test
    public void selfCertificateSslGetWithKeyManagerFactoryAndTrustManagerFactoryTest() throws Exception {
        String uri = "https://liby.com/";
        KeyStore trustStore = KeyStoreUtil.loadCertificate(CertificateType.PKCS12, "C:/Program Files/Java/jdk-11.0.9/lib/security/cacerts", "changeit".toCharArray());
        TrustManagerFactory trustManagerFactory = KeyStoreUtil.initSunX509TrustManagerFactory(trustStore);
        HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, Collections.emptyMap(), null, trustManagerFactory );
        System.out.println(entity);
    }

    @Test
    public void getBaiduIndexTest() throws IOException {
        String url = "https://www.baidu.com";
        HttpResponseEntity responseEntity = HttpClientUtil.get(url);
        System.out.println(responseEntity);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getHeader(HttpHeaders.CONTENT_TYPE));
        logger.info("content: {}", responseEntity.getContent());
    }


    @Test
    public void loginPostTest() throws IOException {
        String url = "http://127.0.0.1:18080/user/login";
        Map<String, String> params = new HashMap<>();
        params.put("username", "root");
        params.put("password", "123456");
        HttpResponseEntity responseEntity = HttpClientUtil.post(url, null, params, ParamFormat.APPLICATION_JSON);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("headers: {}", Arrays.toString(responseEntity.getHeaders()));
        logger.info("content: {}", responseEntity.getContent());
    }

    @Test
    public void contractListTest() throws IOException {
        String url = "http://api.yt.liby.ltd:18080/contract/list/adapter/order_by_create_time_desc";
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "1");
        params.put("pageSize", "2");
        HttpResponseEntity responseEntity = HttpClientUtil.get(url, params);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("headers: {}", Arrays.toString(responseEntity.getHeaders()));
        logger.info("content: {}", responseEntity.getContent());
    }

    @Test
    public void multipartPostTest() throws IOException {
        String url = "http://127.0.0.1:8080/file";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "备案");
        params.put("file", new File("/home/amen/Desktop/备案.png"));

        HttpResponseEntity responseEntity = HttpClientUtil.post(url, params, ParamFormat.FORM_DATA);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("headers: {}", Arrays.toString(responseEntity.getHeaders()));
        logger.info("content: {}", responseEntity.getContent());
    }
    
    @Test
    public void juHeIpQueryTest() throws Exception {
        String url = "https://apis.juhe.cn/ip/Example/query.php";
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            params.put("IP", "123.113.187." + i);
            HttpResponseEntity responseEntity = HttpClientUtil.post(url, params, ParamFormat.URL_FORM_ENCODE);
            System.out.println(responseEntity);
            System.out.println("=====================================================");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
        }
    }

    @Test
    public void aiWenIpQueryTest() throws Exception {
        String searchString = "地址：";
        String url = "https://www.chaipip.com/aiwen.html";
        Map<String, Object> params = new HashMap<>();
        params.put("geetest_challenge", "869d2a864f7b0ebab2291c9aaa2325da");
        params.put("geetest_seccode", "869d2a864f7b0ebab2291c9aaa2325da|jordan");
        params.put("cnm", "RmloQVVEaXZqY3d2RmVzTmpFMk5UTTJOVFV5T0E9PUFUdFB1c3hQc0ZEjQCUr");
        for (int i = 0; i < 200; i++) {
            params.put("ip", "111.225.128." + i);
            HttpResponseEntity responseEntity = HttpClientUtil.post(url, params, ParamFormat.URL_FORM_ENCODE);
            String body = Objects.toString(responseEntity.getContent());
            int ip2regionIndex = body.indexOf("IP2Region");
            int startIndex = body.indexOf(searchString, ip2regionIndex);
            startIndex = startIndex + searchString.length();
            int endIndex = body.indexOf("<", startIndex);
            String ip2region = body.substring(startIndex, endIndex).trim();
            System.out.println(ip2region);
            System.out.println("=====================================================");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }
    }

    @Test
    public void ip138QueryTest() throws Exception {
        byte[] charsetIdentityStartBytes = "<meta charset=\"".getBytes();
        byte[] charsetIdentityEndBytes = "\"".getBytes();
        String ipFormat = "27.23.180.%d";
        String url = "https://ip138.com/iplookup.asp?action=2&ip=";
        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", "https://ip138.com/iplookup.asp?ip=27.22.79.214&action=2");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36");
        String ipResultIdentityStringStart = "var ip_result =";
        String ipResultIdentityStringEnd = ";";

        for (int i = 0; i < 200; i++) {
            String ip = String.format(ipFormat, i);
            HttpResponseEntity responseEntity = HttpClientUtil.sslGetInSecure(url + ip, headers, Collections.emptyMap());
            byte[] content = responseEntity.getContent();
            int startIndex = ArrayUtil.binarySearch(content, charsetIdentityStartBytes);
            startIndex = startIndex + charsetIdentityStartBytes.length;
            int endIndex = ArrayUtil.binarySearch(content, 0, content.length, charsetIdentityEndBytes, 0, charsetIdentityEndBytes.length, startIndex);
            byte[] charsetBytes = Arrays.copyOfRange(content, startIndex, endIndex);
            String charset = new String(charsetBytes);
            String html = new String(content, charset);

            int resultStartIndex = html.indexOf(ipResultIdentityStringStart);
            resultStartIndex = resultStartIndex + ipResultIdentityStringStart.length();
            int resultEndIndex = html.indexOf(ipResultIdentityStringEnd, resultStartIndex);
            String resultJson = html.substring(resultStartIndex, resultEndIndex).trim();
            System.out.printf("ip: %s, detail: %s%n", ip, resultJson);
            System.out.println("=====================================================");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        }
    }

    @Test
    public void testCloudFlare() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("host", "paoluz.link");
        HttpResponseEntity responseEntity = HttpClientUtil.sslGetInSecure(("http://172.67.161.210:80/auth/login"), headers, Collections.emptyMap());
        System.out.println(new String(responseEntity.getContent()));
    }

    @Test
    public void proxyTest() throws Exception {
        HttpClientUtilCustomizer.connectTimeout = 5000000;
        HttpClientUtilCustomizer.soTimeout = 5000000;
        HttpClientUtilCustomizer.proxy = new HttpHost("127.0.0.1", 1087);
        HttpResponseEntity entity = HttpClientUtil.sslGetInSecure("https://www.baidu.com");
        System.out.println(new String(entity.getContent()));
        HttpClientUtil.destroy();
    }

}
