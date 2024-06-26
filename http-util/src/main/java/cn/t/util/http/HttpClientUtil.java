package cn.t.util.http;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.JsonUtil;
import cn.t.util.common.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
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
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final SSLConnectionSocketFactory inSecureSslConnectionSocketFactory;
    static {
        SSLContextBuilder builder = SSLContextBuilder.create();
        try {
            builder.loadTrustMaterial(null, (chain, authType) -> true);
            SSLContext sslContext = builder.build();
            inSecureSslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    private static final SocketConfig defaultSocketConfig = createSocketConfig();
    private static final PoolingHttpClientConnectionManager defaultConnectionManager = createConnectionManager();
    private static final PoolingHttpClientConnectionManager defaultInSecureConnectionManager = createInSecureSslConnectionManager();
    private static final RequestConfig defaultRequestConfig = createRequestConfig();
    private static final CloseableHttpClient defaultHttpClient = createDefaultHttpClient();
    private static final CloseableHttpClient defaultInSecureHttpClient = createDefaultInSecureHttpClient();

    public static HttpResponseEntity get(String uri) throws IOException {
        return get(uri, null);
    }

    public static HttpResponseEntity get(String uri, Map<String, ?> params) throws IOException {
        return get(uri, null, params);
    }

    public static HttpResponseEntity get(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return get(uri, headers, params, false);
    }

    public static HttpResponseEntity get(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return get(defaultHttpClient, uri, headers, params, encode);
    }

    public static HttpResponseEntity get(HttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeGet(client, uri, headers, params, encode);
    }

    public static HttpResponseEntity sslGetInSecure(String uri) throws IOException {
        return sslGetInSecure(uri, null);
    }

    public static HttpResponseEntity sslGetInSecure(String uri, Map<String, ?> params) throws IOException {
        return sslGetInSecure(uri, null, params);
    }

    public static HttpResponseEntity sslGetInSecure(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return sslGetInSecure(uri, headers, params, false);
    }

    public static HttpResponseEntity sslGetInSecure(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeGet(defaultInSecureHttpClient, uri, headers, params, encode);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, null, params, encode, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslGetWithKeyManagerFactoryAndTrustManagerFactory(uri, headers, params, false, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslGetWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executeGet(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, encode);
    }

    private static HttpResponseEntity executeGet(HttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        if (!CollectionUtil.isEmpty(params)) {
            uri += (uri.contains("?") ? "&" + encodeToParamString(params, encode) : "?" + encodeToParamString(params, encode));
        }
        HttpGet httpGet = new HttpGet(uri);
        setHeaders(httpGet, headers);
        return executeRequest(httpClient, httpGet);
    }

    public static HttpResponseEntity delete(String uri) throws IOException {
        return delete(uri, Collections.emptyMap());
    }

    public static HttpResponseEntity delete(String uri, Map<String, ?> params) throws IOException {
        return delete(uri, null, params);
    }

    public static HttpResponseEntity delete(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return delete(uri, headers, params, false);
    }

    public static HttpResponseEntity delete(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return delete(defaultHttpClient, uri, headers, params, encode);
    }

    public static HttpResponseEntity delete(HttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeDelete(client, uri, headers, params, encode);
    }

    public static HttpResponseEntity sslDeleteInSecure(String uri, Map<String, ?> params) throws IOException {
        return sslDeleteInSecure(uri, null, params);
    }

    public static HttpResponseEntity sslDeleteInSecure(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return sslDeleteInSecure(uri, headers, params, false);
    }

    public static HttpResponseEntity sslDeleteInSecure(String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        return executeDelete(defaultInSecureHttpClient, uri, headers, params, encode);
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

    private static HttpResponseEntity executeDelete(HttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, boolean encode) throws IOException {
        if (!CollectionUtil.isEmpty(params)) {
            uri += (uri.contains("?") ? encodeToParamString(params, encode) : "?" + encodeToParamString(params, encode));
        }
        HttpDelete httpDelete = new HttpDelete(uri);
        setHeaders(httpDelete, headers);
        return executeRequest(httpClient, httpDelete);
    }


    public static HttpResponseEntity post(String uri, Map<String, ?> params) throws IOException {
        return post(uri, null, params);
    }

    public static HttpResponseEntity post(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return post(uri, headers, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity post(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return post(uri, null, params, paramFormat);
    }

    public static HttpResponseEntity post(String uri, String body) throws IOException {
        return post(uri, null, body);
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

    public static HttpResponseEntity post(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return post(defaultHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity post(HttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePost(client, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPostInSecure(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return sslPostInSecure(uri, null, params, paramFormat);
    }

    public static HttpResponseEntity sslPostInSecure(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePost(defaultInSecureHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPostWithKeyManagerFactoryAndTrustManagerFactory(uri, null, params, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePost(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPostInSecure(String uri, String body, ParamFormat paramFormat) throws IOException {
        return sslPostInSecure(uri, null, body, paramFormat);
    }

    public static HttpResponseEntity sslPostInSecure(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePost(defaultHttpClient, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPostWithKeyManagerFactoryAndTrustManagerFactory(uri, null, body, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPostWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePost(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, body, paramFormat);
    }

    private static HttpResponseEntity executePost(HttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        setHeaders(httpPost, headers);
        setParams(httpPost, paramFormat, params);
        return executeRequest(httpClient, httpPost);
    }

    private static HttpResponseEntity executePost(HttpClient httpClient, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        setHeaders(httpPost, headers);
        setBody(httpPost, paramFormat, body);
        return executeRequest(httpClient, httpPost);
    }

    public static HttpResponseEntity put(String uri, Map<String, ?> params) throws IOException {
        return put(uri, null, params);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, Map<String, ?> params) throws IOException {
        return put(uri, headers, params, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return put(defaultHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity put(HttpClient client, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePut(client, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity put(String uri, String body) throws IOException {
        return put(uri, null, body);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, String body) throws IOException {
        return put(uri, headers, body, ParamFormat.URL_FORM_ENCODE);
    }

    public static HttpResponseEntity put(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return put(defaultHttpClient, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity put(HttpClient client, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePut(client, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPutInsecure(String uri, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return sslPutInsecure(uri, null, params, paramFormat);
    }

    public static HttpResponseEntity sslPutInsecure(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        return executePut(defaultInSecureHttpClient, uri, headers, params, paramFormat);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPutWithKeyManagerFactoryAndTrustManagerFactory(uri, null, params, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePut(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, params, paramFormat);
    }

    private static HttpResponseEntity executePut(HttpClient httpClient, String uri, Map<String, String> headers, Map<String, ?> params, ParamFormat paramFormat) throws IOException {
        HttpPut httpPut = new HttpPut(uri);
        setHeaders(httpPut, headers);
        setParams(httpPut, paramFormat, params);
        return executeRequest(httpClient, httpPut);
    }

    public static HttpResponseEntity sslPutInSecure(String uri, String body, ParamFormat paramFormat) throws IOException {
        return sslPutInSecure(uri, null, body, paramFormat);
    }

    public static HttpResponseEntity sslPutInSecure(String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        return executePut(defaultHttpClient, uri, headers, body, paramFormat);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return sslPutWithKeyManagerFactoryAndTrustManagerFactory(uri, null, body, paramFormat, keyManagerFactory, trustManagerFactory);
    }

    public static HttpResponseEntity sslPutWithKeyManagerFactoryAndTrustManagerFactory(String uri, Map<String, String> headers, String body, ParamFormat paramFormat, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws IOException, KeyManagementException {
        return executePut(createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(keyManagerFactory, trustManagerFactory), uri, headers, body, paramFormat);
    }

    private static HttpResponseEntity executePut(HttpClient httpClient, String uri, Map<String, String> headers, String body, ParamFormat paramFormat) throws IOException {
        HttpPut httpPut = new HttpPut(uri);
        setHeaders(httpPut, headers);
        setBody(httpPut, paramFormat, body);
        return executeRequest(httpClient, httpPut);
    }

    private static HttpResponseEntity executeRequest(HttpClient httpClient, HttpUriRequest request) throws IOException {
        HttpResponse response = httpClient.execute(request);
//        logger.debug("request: {}, response: {}", request, response);
        try {
            return buildHttpResponseEntity(request, response);
        } finally {
            if(response instanceof CloseableHttpResponse) {
                ((CloseableHttpResponse)response).close();
            }
        }
    }

    private static void setHeaders(HttpRequest request, Map<String, String> headers) {
        if (!CollectionUtil.isEmpty(headers)) {
            headers.forEach(request::setHeader);
        }
    }

    private static void setParams(HttpEntityEnclosingRequest request, ParamFormat format, Map<String, ?> params) throws JsonProcessingException {
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
                request.setEntity(new UrlEncodedFormEntity(paramMap, StandardCharsets.UTF_8));
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

    private static HttpClient createHttpClientWithKeyManagerFactoryAndTrustManagerFactory(KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws KeyManagementException {
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

    private static HttpResponseEntity buildHttpResponseEntity(HttpUriRequest request, HttpResponse response) throws IOException {
        HttpResponseEntity responseEntity = new HttpResponseEntity();
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        responseEntity.setCode(statusLine.getStatusCode());
        responseEntity.setHeaders(response.getAllHeaders());
        if (entity != null) {
            responseEntity.setContent(EntityUtils.toByteArray(entity));
        }
        responseEntity.setUri(request.getURI());
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
            param.forEach((k, v) -> builder.append("&").append(k).append("=").append(v));
        }
        return builder.deleteCharAt(0).toString();
    }

    private static SocketConfig createSocketConfig() {
        return SocketConfig
            .custom()
            .setSoTimeout(HttpClientUtilCustomizer.soTimeout)
            .setTcpNoDelay(HttpClientUtilCustomizer.tcpNoDelay)
            .build();
    }

    private static PoolingHttpClientConnectionManager createConnectionManager() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
            .<ConnectionSocketFactory>create()
            .register("http", HttpClientUtilCustomizer.plainConnectionSocketFactory)
            .register("https", HttpClientUtilCustomizer.sslConnectionSocketFactory)
            .build();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, HttpClientUtilCustomizer.defaultHttpConnectionFactory, HttpClientUtilCustomizer.dnsResolver);
        manager.setMaxTotal(HttpClientUtilCustomizer.maxTotal);
        manager.setDefaultMaxPerRoute(HttpClientUtilCustomizer.maxPerRoute);
        manager.setValidateAfterInactivity(HttpClientUtilCustomizer.validateAfterInactivity);
        //默认socket配置
        manager.setDefaultSocketConfig(defaultSocketConfig);
        return manager;
    }

    private static PoolingHttpClientConnectionManager createInSecureSslConnectionManager() {
        try {
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", HttpClientUtilCustomizer.plainConnectionSocketFactory)
                .register("https", inSecureSslConnectionSocketFactory)
                .build();
            PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, HttpClientUtilCustomizer.defaultHttpConnectionFactory, HttpClientUtilCustomizer.dnsResolver);
            manager.setMaxTotal(HttpClientUtilCustomizer.maxTotal);
            manager.setDefaultMaxPerRoute(HttpClientUtilCustomizer.maxPerRoute);
            manager.setValidateAfterInactivity(HttpClientUtilCustomizer.validateAfterInactivity);
            //默认socket配置
            manager.setDefaultSocketConfig(defaultSocketConfig);
            return manager;
        } catch (Exception e) {
            logger.warn("InSecureSslConnectionManager创建失败", e);
            return defaultConnectionManager;
        }
    }

    public static CloseableHttpClient createInSecureHttpClient(String proxyHost, Integer proxyPort, int connectTimeout, int socketTimeout) {
        RequestConfig.Builder requestConfigBuilder = RequestConfig
            .custom()
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .setCookieSpec(CookieSpecs.STANDARD);
        if(proxyHost != null && proxyPort != null) {
            requestConfigBuilder.setProxy(new HttpHost(proxyHost, proxyPort));
        }
        return HttpClientBuilder.create()
            .setDefaultRequestConfig(requestConfigBuilder.build())
            .setSSLSocketFactory(inSecureSslConnectionSocketFactory)
            .build();
    }

    private static RequestConfig createRequestConfig() {
        //默认请求配置
        return RequestConfig.custom()
            .setConnectTimeout(HttpClientUtilCustomizer.connectTimeout)
            .setSocketTimeout(HttpClientUtilCustomizer.soTimeout)
            .setConnectionRequestTimeout(HttpClientUtilCustomizer.connectionRequestTimeout)
            .setMaxRedirects(HttpClientUtilCustomizer.maxRedirects)
            .setCookieSpec(HttpClientUtilCustomizer.cookieSpec)
            .setProxy(HttpClientUtilCustomizer.proxy)
            .build();
    }

    private static CloseableHttpClient createDefaultHttpClient() {
        return HttpClients.custom()
            .setConnectionManager(defaultConnectionManager)
            .setConnectionManagerShared(false)
            .evictIdleConnections(HttpClientUtilCustomizer.maxIdleTimeInSeconds, TimeUnit.SECONDS)
            .evictExpiredConnections()
            .setDefaultRequestConfig(defaultRequestConfig)
            .setConnectionReuseStrategy(HttpClientUtilCustomizer.connectionReuseStrategy)
            .setKeepAliveStrategy(HttpClientUtilCustomizer.connectionKeepAliveStrategy)
            .setRetryHandler(HttpClientUtilCustomizer.httpRequestRetryHandler)
            .setDefaultCookieStore(HttpClientUtilCustomizer.cookieStore)
            .setDefaultHeaders(HttpClientUtilCustomizer.defaultHeaders)
            .build();
    }
    private static CloseableHttpClient createDefaultInSecureHttpClient() {
        return HttpClients.custom()
            .setConnectionManager(defaultInSecureConnectionManager)
            .setConnectionManagerShared(false)
            .evictIdleConnections(HttpClientUtilCustomizer.maxIdleTimeInSeconds, TimeUnit.SECONDS)
            .evictExpiredConnections()
            .setDefaultRequestConfig(defaultRequestConfig)
            .setConnectionReuseStrategy(HttpClientUtilCustomizer.connectionReuseStrategy)
            .setKeepAliveStrategy(HttpClientUtilCustomizer.connectionKeepAliveStrategy)
            .setRetryHandler(HttpClientUtilCustomizer.httpRequestRetryHandler)
            .build();
    }

    public static void destroy() {
        if(defaultHttpClient != null) {
            try { defaultHttpClient.close();} catch (IOException ignore) {}
        }
        if(defaultInSecureHttpClient != null) {
            try { defaultInSecureHttpClient.close();} catch (IOException ignore) {}
        }
    }
}
