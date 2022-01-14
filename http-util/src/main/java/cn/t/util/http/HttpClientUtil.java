package cn.t.util.http;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.JsonUtil;
import cn.t.util.common.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http重定向
 * 默认策略为{@link DefaultRedirectStrategy}，只支持HEAD, GET重定向, 如果需要全部请求重定向可以使用策略{@link LaxRedirectStrategy}
 *
 * http链接复用
 * 1.建立连接对象并设置请求头
 * HttpPost httpPost = new HttpPost("xxxxx");
 * httpPost.addHeader("Connection", "keep-alive");
 * 2.使用EntityUtils.consume消费响应内容使当前链接可复用
 * CloseableHttpResponse response = httpClient.execute(httpPost);
 * if(null != response.getEntity()){
 * EntityUtils.consume(response.getEntity());
 * }
 */
public class HttpClientUtil {

    private static final CloseableHttpClient defaultHttpClient = createDefaultHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static HttpResponseEntity get(String uri) throws IOException {
        return get(uri, null, null, false);
    }

    public static HttpResponseEntity get(String uri, Map<String, ?> params) throws IOException {
        return get(uri, null, params, false);
    }

    public static HttpResponseEntity get(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return get(uri, headers, params, false);
    }

    public static HttpResponseEntity get(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeGet(defaultHttpClient, uri, headers, params, encode);
    }

    public static HttpResponseEntity get(CloseableHttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeGet(client, uri, headers, params, encode);
    }

    public static HttpResponseEntity sslGetWithoutCertificateCheck(String uri, Map<String, ?> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslGetWithoutCertificateCheck(uri, null, params, false);
    }

    public static HttpResponseEntity sslGetWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslGetWithoutCertificateCheck(uri, headers, params, false);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executeGet(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, null, params, encode);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, headers, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executeGet(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, encode);
    }

    public static HttpResponseEntity sslGetWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executeGet(createHttpClientWithoutCertificateCheck(), uri, headers, params, encode);
    }

    private static HttpResponseEntity executeGet(CloseableHttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        if (!CollectionUtil.isEmpty(params)) {
            uri += (uri.contains("?") ? "&" + encodeToParamString(params, encode) : "?" + encodeToParamString(params, encode));
        }
        HttpGet httpGet = new HttpGet(uri);
        setHeaders(httpGet, headers);
        return executeRequest(httpClient, httpGet);
    }

    public static HttpResponseEntity delete(String uri, Map<String, ?> params) throws IOException {
        return delete(uri, null, params, false);
    }

    public static HttpResponseEntity delete(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return delete(uri, headers, params, false);
    }

    public static HttpResponseEntity delete(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeDelete(defaultHttpClient, uri, headers, params, encode);
    }

    public static HttpResponseEntity delete(CloseableHttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeDelete(client, uri, headers, params, encode);
    }

    public static HttpResponseEntity sslDeleteWithoutCertificateCheck(String uri, Map<String, ?> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslDeleteWithoutCertificateCheck(uri, null, params, false);
    }

    public static HttpResponseEntity sslDeleteWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslDeleteWithoutCertificateCheck(uri, headers, params, false);
    }

    public static HttpResponseEntity sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(uri, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executeDelete(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, null, params, encode);
    }

    public static HttpResponseEntity sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(uri, headers, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executeDelete(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, encode);
    }

    public static HttpResponseEntity sslDeleteWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executeDelete(createHttpClientWithoutCertificateCheck(), uri, headers, params, encode);
    }

    private static HttpResponseEntity executeDelete(CloseableHttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        if (!CollectionUtil.isEmpty(params)) {
            uri += (uri.contains("?") ? encodeToParamString(params, encode) : "?" + encodeToParamString(params, encode));
        }
        HttpDelete httpDelete = new HttpDelete(uri);
        setHeaders(httpDelete, headers);
        return executeRequest(httpClient, httpDelete);
    }


    public static HttpResponseEntity post(String uri, Map<String, ?> params) throws IOException {
        return post(uri, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity post(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return post(uri, headers, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity post(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return post(uri, null, params, paramFormat);
    }

    public static HttpResponseEntity post(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePost(defaultHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity post(CloseableHttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePost(client, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPostWithoutCertificateCheck(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslPostWithoutCertificateCheck(uri, null, params, paramFormat);
    }

    public static HttpResponseEntity sslPostWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executePost(createHttpClientWithoutCertificateCheck(), uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPostWithKeyManagerFactoryAndTrustManagerFactory(uri, null, params, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePost(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity post(String uri, String body) throws IOException {
        return post(uri, body, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity post(String uri, Map<String, String> headers, String body) throws IOException {
        return post(uri, headers, body, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity post(String uri, String body, ParamFormat paramFormat) throws IOException {
        return post(uri, null, body, paramFormat);
    }

    public static HttpResponseEntity post(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePost(defaultHttpClient, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPostWithoutCertificateCheck(String uri, String body, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslPostWithoutCertificateCheck(uri, null, body, paramFormat);
    }

    public static HttpResponseEntity sslPostWithoutCertificateCheck(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executePost(createHttpClientWithoutCertificateCheck(), uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPostWithKeyManagerFactoryAndTrustManagerFactory(uri, null, body, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePost(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, body, paramFormat);
    }

    private static HttpResponseEntity executePost(CloseableHttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        setHeaders(httpPost, headers);
        setParams(httpPost, paramFormat, params);
        return executeRequest(httpClient, httpPost);
    }

    private static HttpResponseEntity executePost(CloseableHttpClient httpClient, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        setHeaders(httpPost, headers);
        setBody(httpPost, paramFormat, body);
        return executeRequest(httpClient, httpPost);
    }

    public static HttpResponseEntity put(String uri, Map<String, ?> params) throws IOException {
        return put(uri, null, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return put(uri, headers, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePut(defaultHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity put(CloseableHttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePut(client, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPutWithoutCertificateCheck(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executePut(createHttpClientWithoutCertificateCheck(), uri, null, params, paramFormat);
    }

    public static HttpResponseEntity sslPutWithoutCertificateCheck(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executePut(createHttpClientWithoutCertificateCheck(), uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePut(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, null, params, paramFormat);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePut(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, paramFormat);
    }

    private static HttpResponseEntity executePut(CloseableHttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        HttpPut httpPut = new HttpPut(uri);
        setHeaders(httpPut, headers);
        setParams(httpPut, paramFormat, params);
        return executeRequest(httpClient, httpPut);
    }

    public static HttpResponseEntity put(String uri, String body) throws IOException {
        return put(uri, null, body, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, String body) throws IOException {
        return put(uri, headers, body, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePut(defaultHttpClient, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity put(CloseableHttpClient client, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePut(client, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPutWithoutCertificateCheck(String uri, String body, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return sslPutWithoutCertificateCheck(uri, null, body, paramFormat);
    }

    public static HttpResponseEntity sslPutWithoutCertificateCheck(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        return executePut(createHttpClientWithoutCertificateCheck(), uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPutWithKeyManagerFactoryAndTrustManagerFactory(uri, null, body, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePut(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, body, paramFormat);
    }

    private static HttpResponseEntity executePut(CloseableHttpClient httpClient, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        HttpPut httpPut = new HttpPut(uri);
        setHeaders(httpPut, headers);
        setBody(httpPut, paramFormat, body);
        return executeRequest(httpClient, httpPut);
    }

    private static HttpResponseEntity executeRequest(CloseableHttpClient httpClient, HttpUriRequest request) throws IOException {
        RequestLine requestLine = request.getRequestLine();
        logger.trace("Executing request: {}", requestLine);
        HttpContext context = new BasicHttpContext();
        try (
            CloseableHttpResponse response = httpClient.execute(request, context)
        ) {
            return buildHttpResponseEntity(response, context);
        }
    }

    private static void setHeaders(HttpRequest request, Map<String, String> headers) {
        if (!CollectionUtil.isEmpty(headers)) {
            headers.forEach(request::setHeader);
        }
    }

    private static void setParams(HttpEntityEnclosingRequest request, ParamFormat format, Map<String, ?> params) throws JsonProcessingException, UnsupportedEncodingException {
        if (!CollectionUtil.isEmpty(params)) {
            if (ParamFormat.APPLICATION_JSON == format) {
                request.setEntity(new StringEntity(JsonUtil.serialize(params), ContentType.APPLICATION_JSON));
            } else if (ParamFormat.FORM_DATA == format) {
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                params.forEach((k, v) -> {
                    if (v instanceof File) {
                        File file = (File) v;
                        builder.addBinaryBody(k, file);
                    } else {
                        builder.setCharset(Charset.defaultCharset());
                        builder.addTextBody(k, v.toString(), ContentType.create(ContentType.DEFAULT_TEXT.getMimeType(), "UTF-8"));
                    }
                });
                HttpEntity entity = builder.build();
                request.setEntity(entity);
            } else {
                List<NameValuePair> paramMap = new ArrayList<>();
                params.forEach((k, v) -> paramMap.add(new BasicNameValuePair(k, v == null ? "" : v.toString())));
                request.setEntity(new UrlEncodedFormEntity(paramMap));
            }
        }
    }

    private static void setBody(HttpEntityEnclosingRequest request, ParamFormat format, String content) {
        if (!StringUtil.isEmpty(content)) {
            if (ParamFormat.APPLICATION_JSON == format) {
                request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
            } else {
                request.setEntity(new StringEntity(content, ContentType.create(ContentType.DEFAULT_TEXT.getMimeType(), "UTF-8")));
            }
        }
    }

    private static RequestConfig defaultRequestConfig() {
        return RequestConfig.custom()
            .setConnectTimeout(1500)
            .setSocketTimeout(5000)
            .setCookieSpec(CookieSpecs.STANDARD)
            .build();
    }

    private static CloseableHttpClient createDefaultHttpClient() {
        return HttpClientBuilder
            .create()
            .setDefaultRequestConfig(defaultRequestConfig())
            .build();
    }

    private static CloseableHttpClient createHttpClientWithoutCertificateCheck() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, (chain, authType) -> true);
        SSLContext sslContext = builder.build();
        return HttpClients
            .custom()
            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
            .setDefaultRequestConfig(defaultRequestConfig()).build();
    }

    private static CloseableHttpClient createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws KeyManagementException {
        SSLContext sslContext = SSLContexts.createDefault();
        KeyManager[] keyManagers = null;
        TrustManager[] trustManagers = null;
        if(keyManagerFactory != null) {
            keyManagers = keyManagerFactory.getKeyManagers();
        }
        if(trustManagerFactory != null) {
            trustManagers = trustManagerFactory.getTrustManagers();
        }
        sslContext.init(keyManagers, trustManagers, null);
        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", sslConnectionFactory)
            .build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setSSLSocketFactory(sslConnectionFactory);
        builder.setConnectionManager(new PoolingHttpClientConnectionManager(registry));
        builder.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build());
        return builder.build();

    }

    private static HttpResponseEntity buildHttpResponseEntity(CloseableHttpResponse response, HttpContext context) throws IOException {
        HttpResponseEntity responseEntity = new HttpResponseEntity();
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        responseEntity.setCode(statusLine.getStatusCode());
        Header contentTypeHeader = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
        ContentType ct = null;
        if (contentTypeHeader != null) {
            responseEntity.setContentType(contentTypeHeader.getValue());
            ct = ContentType.parse(contentTypeHeader.getValue());
        }
        responseEntity.setHeaders(response.getAllHeaders());
        if (entity != null) {
            if (isStringContent(ct)) {
                Charset charset = ct.getCharset() != null ? ct.getCharset() : Charset.defaultCharset();
                responseEntity.setContent(EntityUtils.toString(entity, charset));
            } else {
                responseEntity.setContent(EntityUtils.toByteArray(entity));
            }
        }
        HttpUriRequest currentRequest = (HttpUriRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);
        if(currentRequest.getURI().isAbsolute()) {
            responseEntity.setUrl(currentRequest.getURI().toString());
        } else {
            HttpHost currentHost = (HttpHost)  context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
            responseEntity.setUrl(currentHost.toURI() + currentRequest.getURI());
        }
        EntityUtils.consume(entity);
        return responseEntity;
    }

    public static String encodeToParamString(Map<String, ?> param, boolean encode) {
        if (param == null || param.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        if (encode) {
            param.forEach((k, v) -> {
                builder.append("&").append(k).append("=");
                try {
                    builder.append(URLEncoder.encode(v.toString(), Charset.defaultCharset().displayName()));
                } catch (UnsupportedEncodingException e) {
                    logger.error("encode value failed", e);
                    throw new RuntimeException(e);
                }
            });
        } else {
            param.forEach((k, v) -> {
                builder.append("&").append(k).append("=").append(v);
            });
        }
        return builder.deleteCharAt(0).toString();
    }

    private static boolean isStringContent(ContentType ct) {
        return ct != null && STRING_CONTENT_LIST.parallelStream().anyMatch(t -> t.getMimeType().equalsIgnoreCase(ct.getMimeType()));
    }

    private static final List<ContentType> STRING_CONTENT_LIST = new ArrayList<>();

    static {
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_ATOM_XML);
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_FORM_URLENCODED);
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_JSON);
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_SVG_XML);
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_XHTML_XML);
        STRING_CONTENT_LIST.add(ContentType.APPLICATION_XML);
        STRING_CONTENT_LIST.add(ContentType.TEXT_HTML);
        STRING_CONTENT_LIST.add(ContentType.TEXT_PLAIN);
        STRING_CONTENT_LIST.add(ContentType.TEXT_XML);
    }

}
