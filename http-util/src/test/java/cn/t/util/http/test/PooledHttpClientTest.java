package cn.t.util.http.test;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2020-12-22 20:13
 **/
public class PooledHttpClientTest {

    private static final Logger logger = LoggerFactory.getLogger(PooledHttpClientTest.class);
    private static final String SUCCESS_STATUS = "1";

    private static final List<String> headerList = Stream.of("城市", "区域", "学校名称", "地址", "联系电话").collect(Collectors.toList());
    private static final List<String> keyList = Stream.of("city", "area", "name", "address", "tel").collect(Collectors.toList());

    private static final CloseableHttpClient httpClient;
    static {
        //注册访问协议相关的Socket工厂
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
            .<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
            .build();
        //HttpConnection 工厂:配置写请求/解析响应处理器
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);
        //DNS 解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        //创建池化连接管理器
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
        //默认为Socket配置
        SocketConfig defaultSocketConfig = SocketConfig
            .custom()
            .setTcpNoDelay(true).build();
        manager.setDefaultSocketConfig(defaultSocketConfig);
        //设置整个连接池的最大连接数
        manager.setMaxTotal(100);
        //每个路由的默认最大连接，每个路由实际最大连接数由DefaultMaxPerRoute控制，而MaxTotal是整个池子的最大数
        //设置过小无法支持大并发(ConnectionPoolTimeoutException) Timeout waiting for connection from pool
        //每个路由的最大连接数
        manager.setDefaultMaxPerRoute(40);
        //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
        manager.setValidateAfterInactivity(5 * 1000);
        //默认请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
            //设置连接超时时间，2s
            .setConnectTimeout(3 * 1000)
            //设置等待数据超时时间，5s
            .setSocketTimeout(5 * 1000)
            //设置从连接池获取连接的等待超时时间
            .setConnectionRequestTimeout(2000)
            .setCookieSpec(CookieSpecs.STANDARD)
            .build();
        SSLContextBuilder builder = new SSLContextBuilder();
        SSLContext sslContext = null;
        try {
            builder.loadTrustMaterial(null, (chain, authType) -> true);
            sslContext = builder.build();
        } catch (Exception e) {
            logger.error("", e);
        }
        if(sslContext == null) {
            httpClient = HttpClients.createDefault();
        } else {
            httpClient = HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
                .setConnectionManager(manager)
                //连接池不是共享模式
                .setConnectionManagerShared(false)
                //定期回收空闲连接
                .evictIdleConnections(30, TimeUnit.SECONDS)
                //定期回收过期连接
                .evictExpiredConnections()
                //设置默认请求配置
                .setDefaultRequestConfig(defaultRequestConfig)
                //连接重用策略，即是否能keepAlive
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                //长连接配置，即获取长连接生产多长时间
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                //设置重试次数，默认是3次，当前是禁用掉（根据需要开启）
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (httpClient != null) { httpClient.close();}
                } catch (IOException e) {
                    logger.error("error when close httpClient", e);
                }
            }
        });
    }
}
