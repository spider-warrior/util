package cn.t.util.web;

import javax.servlet.http.Cookie;
import java.util.function.Consumer;

public enum CookieOption {

    SECURE_HTTPS((Cookie cookie) -> {
        cookie.setSecure(true);
    }),

    HTTP_ONLY((Cookie cookie) -> {
        cookie.setHttpOnly(true);
    }),

    ;

    public void configCookie(Cookie cookie) {
        consumer.accept(cookie);
    }

    private final Consumer<Cookie> consumer;

    CookieOption(Consumer<Cookie> consumer) {
        this.consumer = consumer;
    }
}

