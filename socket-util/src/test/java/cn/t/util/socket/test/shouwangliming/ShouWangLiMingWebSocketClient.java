package cn.t.util.socket.test.shouwangliming;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ShouWangLiMingWebSocketClient extends WebSocketClient {


    public static void main(String[] args) throws Exception {
//        org.java_websocket.WebSocketImpl.WebSocketImpl.DEBUG = true;
        String host = "35.193.239.51";
        int port = 8020;
        Map<String, String> header = xiaoDaHao();
        ShouWangLiMingWebSocketClient webSocketClient = new ShouWangLiMingWebSocketClient(URI.create("ws://" + host + ":" + port), header);
        webSocketClient.connect();
        Thread.sleep(100000);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        WebSocketLoginSuccessProcess loginSuccessProcess = new WebSocketLoginSuccessProcess(this);
        loginSuccessProcess.process();
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

    public ShouWangLiMingWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public static Map<String, String> buildDaHao() {
        Map<String, String> header = new HashMap<>();
        header.put("uid", "29187620000481");
        header.put("time", "1565002087028");
        header.put("Origin", "http://35.193.239.51:8020");
        header.put("sign", "540c77dedd94973ba191e5c175248a95");
        header.put("appid", "100002");
        return header;
    }

    public static Map<String, String> xiaoDaHao() {
        Map<String, String> header = new HashMap<>();
        header.put("uid", "76455726000567");
        header.put("time", "1565081101872");
        header.put("Origin", "http://35.193.239.51:8020");
        header.put("sign", "15f3ecd9f3e300cd6dee9702ca2cb67b");
        header.put("appid", "100002");
        return header;
    }
}
