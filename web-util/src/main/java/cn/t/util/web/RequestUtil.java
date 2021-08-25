package cn.t.util.web;

import cn.t.util.common.ArrayUtil;
import cn.t.util.common.RegexUtil;
import cn.t.util.common.StringUtil;
import cn.t.util.common.SystemUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtil {

    private static final Pattern PATTERN = Pattern.compile("https?://(.*?)/");

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(!ArrayUtil.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public static void deleteCookie(HttpServletResponse response, String cookieName) throws UnsupportedEncodingException {
        deleteCookie(response, null, cookieName);
    }

    public static void deleteCookie(HttpServletResponse response, String path, String cookieName) throws UnsupportedEncodingException {
        setCookie(response, null, path, cookieName, null, 0);
    }

    public static void setCookie(HttpServletResponse response, String domain, String cookieName, String cookieValue, Integer maxAge) throws UnsupportedEncodingException {
        setCookie(response, domain, null, cookieName, cookieValue, maxAge);
    }

    public static void setCookie(HttpServletResponse response, String domain, String cookieName, String cookieValue, Integer maxAge, CookieOption... options) throws UnsupportedEncodingException {
        setCookie(response, domain, null, cookieName, cookieValue, maxAge, options);
    }

    public static void setCookie(HttpServletResponse response, String domain, String path, String cookieName, String cookieValue, Integer maxAge, CookieOption... options) throws UnsupportedEncodingException {
        setCookie(response, domain, path, cookieName, cookieValue, maxAge, false, options);
    }

    public static void setCookie(HttpServletResponse response, String domain, String path, String cookieName, String cookieValue, Integer maxAge, boolean isEncode, CookieOption... options) throws UnsupportedEncodingException {
        if (cookieValue == null) {
            cookieValue = "";
        } else if (isEncode) {
            cookieValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        }
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        if(!StringUtil.isEmpty(domain)) {
            cookie.setDomain(domain);
        }
        if(StringUtil.isEmpty(path)) {
            cookie.setPath("/");
        } else {
            cookie.setPath(path);
        }
        if(options != null && options.length>0) {
            for(CookieOption cookieOption: options){
                cookieOption.configCookie(cookie);
            }
        }
        response.addCookie(cookie);
    }

    public static String getTopDomain(HttpServletRequest request) {
        return getTopDomain(request.getRequestURL().toString().toLowerCase());
    }

    public static String getTopDomain(String url) {
        Matcher matcher = PATTERN.matcher(url);
        boolean success = matcher.find();
        if(!success) {
            throw new RuntimeException("could not find domain for: " + url);
        } else {
            String domain = matcher.group(1);
            domain = domain.split(":")[0];
            if(RegexUtil.isIp(domain)) {
                return domain;
            } else {
                StringBuilder builder = new StringBuilder(domain);
                int count = 0;
                for(int i=builder.length()-1; i >= 0; i--) {
                    if(count > 1) {
                        builder.deleteCharAt(i);
                    } else {
                        if(builder.charAt(i) == '.') {
                            count++;
                        }
                    }
                }
                while(builder.charAt(0) == '.') {
                    builder.deleteCharAt(0);
                }
                return builder.toString();
            }
        }
    }

    public String getRemoteAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                ipAddress = SystemUtil.getCashedLocalPrivateIpV4();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.contains(",")) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
