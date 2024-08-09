package cn.t.util.web;

import jakarta.servlet.http.Cookie;

import java.util.function.Consumer;

public enum CookieOption {

    SECURE_HTTPS((Cookie cookie) -> cookie.setSecure(true)),

    HTTP_ONLY((Cookie cookie) -> cookie.setHttpOnly(true)),

    SAME_SITE_NONE((Cookie cookie) -> cookie.setAttribute("SameSite", "None")),
    SAME_SITE_STRICT((Cookie cookie) -> cookie.setAttribute("SameSite", "STRICT")),
    SAME_SITE_LAX((Cookie cookie) -> cookie.setAttribute("SameSite", "Lax"))
    ;

    public void configCookie(Cookie cookie) {
        consumer.accept(cookie);
    }

    private final Consumer<Cookie> consumer;

    CookieOption(Consumer<Cookie> consumer) {
        this.consumer = consumer;
    }
}

