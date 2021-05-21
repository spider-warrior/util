package cn.t.util.socket.test;

import cn.t.util.socket.WebSocketUtil;
import cn.t.util.socket.messaging.stomp.StompClient;
import cn.t.util.socket.messaging.stomp.StompEventHandler;
import cn.t.util.socket.messaging.stomp.StompMessage;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class StompClientTest {

    @Test
    public void testStompClientTest() throws Exception {
        URI uri = new URI("ws://api.nanning.com/socket/543/2ss32xv6/websocket");
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("Cookie", "ticket=7bced9cf7847456893ff7e51dae838f6");
        httpHeaders.put("User-Agent", "jy-client");
        StompClient stompClient = WebSocketUtil.createStompClient(uri, httpHeaders, new EchoStompEventHandler());
        stompClient.connect();
        while (!stompClient.isClosed()) ;
    }
}

class EchoStompEventHandler implements StompEventHandler {
    @Override
    public void onOpen(StompClient client) {
        try {
            client.subscribe("/topic/broadcast");
            client.subscribe("/user/topic/p2p");
            client.send("/app/unit/unit_total", "{}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(StompClient client, StompMessage<String> message) {
        System.out.println("receive messsage: " + message);
    }

    @Override
    public void onClose(StompClient client, int code) {

    }

    @Override
    public void onError(StompClient client, Exception ex) {
        ex.printStackTrace();
    }
}
