package cn.t.util.web.test;

import cn.t.util.web.RequestUtil;
import org.junit.Test;

public class RequestUtilTest {

    @Test
    public void getTopDomainTest() {
        String url1 = "http://124.239.148.161/user/login";
        String url2 = "http://124.239.148.161:8080/user/login";
        String url3 = "http://baidu.com/user/login";
        String url4 = "http://www.baidu.com/user/login";
        String url5 = "http://www.baidu.com:8080/user/login";
        String url6 = "http://a.www.baidu.com:8080/user/login";
        String url7 = "http://b.a.www.baidu.com:8080/user/login";
        String url8 = "https://b.a.www.baidu.com:8080/user/login";
        System.out.println(RequestUtil.getTopDomain(url1));
        System.out.println(RequestUtil.getTopDomain(url2));
        System.out.println(RequestUtil.getTopDomain(url3));
        System.out.println(RequestUtil.getTopDomain(url4));
        System.out.println(RequestUtil.getTopDomain(url5));
        System.out.println(RequestUtil.getTopDomain(url6));
        System.out.println(RequestUtil.getTopDomain(url7));
        System.out.println(RequestUtil.getTopDomain(url8));
    }

}
