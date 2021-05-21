package cn.t.util.http.test;

import cn.t.util.common.JsonUtil;
import cn.t.util.doc.ExcelUtil;
import cn.t.util.http.HttpClientUtil;
import cn.t.util.http.HttpResponseEntity;
import cn.t.util.io.FileUtil;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
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
        SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
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

    public static void main(String[] args) throws IOException {
//        exportBeijingSchoolName();
        exportAnhuiSchoolName();
    }

    private static void exportAnhuiSchoolName() throws IOException {
        List<QueryCity> cityList = Arrays.asList(
            new QueryCity("合肥市", Arrays.asList(new double[]{117.255711,31.606445}, new double[]{117.339659,32.243016}, new double[]{117.640914,31.81396}, new double[]{116.984936,31.99445})),
            new QueryCity("蚌埠市", Arrays.asList(new double[]{117.394276,32.812891}, new double[]{117.179832,32.930306}, new double[]{117.337359,33.312025}, new double[]{117.699556,32.910424})),
            new QueryCity("马鞍山市", Arrays.asList(new double[]{118.534333,31.497737}, new double[]{118.122694,31.663115}, new double[]{118.536633,31.805122}, new double[]{118.705658,31.621306}))
//            new QueryCity("合肥市", new double[]{117.227308,31.82057}),
//            new QueryCity("芜湖市", new double[]{118.376451,31.326319}),
//            new QueryCity("蚌埠市", new double[]{117.363228,32.939667}),
//            new QueryCity("淮南市", new double[]{116.999933,32.625478}),
//            new QueryCity("马鞍山市", new double[]{118.506502,31.669611}),
//            new QueryCity("淮北市", new double[]{116.794664,33.971707}),
//            new QueryCity("铜陵市", new double[]{117.816576,30.929935}),
//            new QueryCity("安庆市", new double[]{117.043551,30.50883}),
//            new QueryCity("黄山市", new double[]{118.317325,29.709239}),
//            new QueryCity("滁州市", new double[]{118.316264,32.303627}),
//            new QueryCity("阜阳市", new double[]{115.819729,32.896969}),
//            new QueryCity("宿州市", new double[]{116.984084,33.633891}),
//            new QueryCity("六安市", new double[]{116.507676,31.752889}),
//            new QueryCity("亳州市", new double[]{115.782939,33.869338}),
//            new QueryCity("池州市", new double[]{117.489157,30.656037}),
//            new QueryCity("宣城市", new double[]{118.757995,30.945667})
        );
        String folder = "C:/Users/36296/Desktop/安徽学校";
        int total = 0;
        int xiaoxueTotal = 0;
        int zhongxueTotal = 0;
        for(QueryCity queryCity: cityList) {
            int xTotal = exportSchoolName(queryCity, "小学", folder);
            int zTotal = exportSchoolName(queryCity, "中学", folder);
            total+=xTotal;
            total+=zTotal;
            xiaoxueTotal+=xTotal;
            zhongxueTotal+=zTotal;
            logger.info("city: {}, 学校导出完毕", queryCity.getCityName());
        }
        logger.info("导出完成, total: {}, 小学总数: {}, 中学总数: {}", total, xiaoxueTotal, zhongxueTotal);
    }

    private static void exportBeijingSchoolName() throws IOException {
        QueryCity queryCity = new QueryCity();
        queryCity.setCityName("北京");
        queryCity.setLocationList(Arrays.asList(new double[]{116.579891,40.078463}, new double[]{116.148677,40.072158}, new double[]{116.145931,39.802599}, new double[]{116.58813,39.781495}));
        int total = exportSchoolName(queryCity, "小学", "C:/Users/36296/Desktop");
        total += exportSchoolName(queryCity, "中学", "C:/Users/36296/Desktop");
        logger.info("导出完成, 总记录数: {}", total);
    }

    @SuppressWarnings("unchecked")
    public static int exportSchoolName(QueryCity queryCity, String type, String folder) throws IOException {
        String city = queryCity.getCityName();
        Set<Object> schoolItemSet = new HashSet<>();
        List<double[]> locationList = queryCity.getLocationList();
        for(double[] location: locationList) {
            int pageSize = 25;
            int index = 1;
            String url = "https://restapi.amap.com/v3/place/around?key=919890c8e8738a0e5c4034c5adfeea76&keywords="+ type +"&location="+ location[0] +","+ location[1] +"&types="+ type +"&city="+ city +"&radius=50000&offset="+ pageSize +"&output=JSON&page=";
            logger.info("query url: {}", url);
            while (true) {
                HttpResponseEntity responseEntity = HttpClientUtil.get(httpClient, url+(index++), Collections.emptyMap(), Collections.emptyMap(), false);
                if (responseEntity.getCode() / 100 == 2) {
                    String responseContent = Objects.toString(responseEntity.getContent()).trim();
                    Map<String, Object> result = JsonUtil.deserialize(responseContent, java.util.Map.class);
                    if(Objects.equals(SUCCESS_STATUS, result.get("status"))) {
                        List<Map<String, Object>> poiList = (List<Map<String, Object>>)result.get("pois");
                        poiList.forEach(data -> {
                            SchoolItem schoolItem = new SchoolItem();
                            schoolItem.setCity(Objects.toString(data.get("cityname")));
                            schoolItem.setArea(Objects.toString(data.get("adname")));
                            schoolItem.setName(Objects.toString(data.get("name")));
                            schoolItem.setAddress(Objects.toString(data.get("address")));
                            schoolItem.setTel(Objects.toString(data.get("tel")));
                            schoolItemSet.add(schoolItem);
                        });
                        if(poiList.size() < pageSize) {
                            break;
                        }
                    } else {
                        logger.warn("查询poi失败: {}, index: {}", url, index - 1);
                        break;
                    }
                } else {
                    logger.warn("查询poi失败: {}, index: {}", url, index - 1);
                    break;
                }
            }
        }
        try (
            Workbook workbook = ExcelUtil.exportWorkbook("小学", headerList, keyList, schoolItemSet);
        ) {
            String folderPath = FileUtil.appendFilePath(folder, city);
            FileUtil.initDirectory(new File(folderPath));
            workbook.write(new FileOutputStream(FileUtil.appendFilePath(folderPath, type) + ".xlsx"));
        }
        logger.info("{}{}数量: {}", city, type, schoolItemSet.size());
        return schoolItemSet.size();
    }

    private static class QueryCity {
        private String cityName;
        private List<double[]> locationList;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public List<double[]> getLocationList() {
            return locationList;
        }

        public void setLocationList(List<double[]> locationList) {
            this.locationList = locationList;
        }

        public QueryCity() {
        }

        public QueryCity(String cityName, List<double[]> locationList) {
            this.cityName = cityName;
            this.locationList = locationList;
        }

        @Override
        public String toString() {
            return "QueryCity{" +
                "cityName='" + cityName + '\'' +
                ", locationList=" + locationList +
                '}';
        }
    }

    private static class SchoolItem {
        private String city;
        private String area;
        private String name;
        private String address;
        private String tel;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SchoolItem that = (SchoolItem) o;
            return Objects.equals(city, that.city) &&
                Objects.equals(area, that.area) &&
                Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city, area, name);
        }

        @Override
        public String toString() {
            return "SchoolItem{" +
                "city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
        }
    }

}
