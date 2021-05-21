package cn.t.util.socket.test;

import cn.t.util.socket.WebSocketUtil;

public class WebSocketUtilTest {
    public static void main(String[] args) {
        String key = "I8erOPjE5EJ4FeejIypKMg==";
        //Eu3sO/4u1tfCrcMatQoTlMsc2zo=
        System.out.println(WebSocketUtil.generateWebSocketAccept(key));
    }
}
