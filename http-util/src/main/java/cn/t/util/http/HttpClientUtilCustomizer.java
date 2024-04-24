package cn.t.util.http;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.Collection;

public class HttpClientUtilCustomizer {
    public static HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
    public static PlainConnectionSocketFactory plainConnectionSocketFactory = PlainConnectionSocketFactory.INSTANCE;
    public static SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault(), hostnameVerifier);
    public static DefaultHttpRequestWriterFactory defaultHttpRequestWriterFactory = DefaultHttpRequestWriterFactory.INSTANCE;
    public static DefaultHttpResponseParserFactory defaultHttpResponseParserFactory = DefaultHttpResponseParserFactory.INSTANCE;
    public static ManagedHttpClientConnectionFactory defaultHttpConnectionFactory = new ManagedHttpClientConnectionFactory(defaultHttpRequestWriterFactory, defaultHttpResponseParserFactory);
    public static DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
    public static ConnectionReuseStrategy connectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
    public static ConnectionKeepAliveStrategy connectionKeepAliveStrategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
    public static HttpRequestRetryHandler httpRequestRetryHandler = new DefaultHttpRequestRetryHandler(0, false);
    public static CookieStore cookieStore = new BasicCookieStore();
    public static Collection<Header> defaultHeaders = new ArrayList<>();
    public static boolean tcpNoDelay = true;
    public static int soTimeout = 10000;
    public static int connectTimeout = 3000;
    public static int connectionRequestTimeout = 2000;
    public static int maxTotal = 20;
    public static int maxPerRoute = 5;
    public static int validateAfterInactivity = 5000;
    public static int maxIdleTimeInSeconds = 30;
    public static int maxRedirects = 5;
    public static String cookieSpec = CookieSpecs.STANDARD;
    public static HttpHost proxy = null;

}
