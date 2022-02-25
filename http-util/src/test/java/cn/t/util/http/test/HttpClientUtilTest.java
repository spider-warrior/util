package cn.t.util.http.test;

import cn.t.util.http.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);

    @Test
    public void getTest() throws IOException {
        String url = "http://127.0.0.1:8080/test";
        Map<String, String> headers = new HashMap<>();
        headers.put("my-header", "this is my header");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "amen");
        HttpResponseEntity responseEntity = HttpClientUtil.get(url, headers, params);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getContentType());
        logger.info("content: {}", responseEntity.getContent());
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
        logger.info("content type: {}", responseEntity.getContentType());
        logger.info("content: {}", responseEntity.getContent());

        logger.info("-------------------------------------------------------------------");

        url = "http://127.0.0.1:8080/test/json_post";
        responseEntity = HttpClientUtil.post(url, headers, params, ParamFormat.APPLICATION_JSON);
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getContentType());
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
//        String uri = "https://liby.ltd/1.png";
        String uri = "http://liby.ltd/";
//        String uri = "https://www.baidu.com";
        HttpResponseEntity entity = HttpClientUtil.get(uri, Collections.emptyMap());
        System.out.println(entity);
    }
    @Test
    public void selfCertificateSslGetWithoutCertificateCheckTest() throws Exception {
        String uri = "https://liby.com/";
        HttpResponseEntity entity = HttpClientUtil.sslGetWithoutCertificateCheck(uri, Collections.emptyMap());
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
        logger.info("status: {}", responseEntity.getCode());
        logger.info("content type: {}", responseEntity.getContentType());
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

}
