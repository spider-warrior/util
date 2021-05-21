package cn.t.util.socket.messaging.stomp;

public interface StompEventHandler {

    void onOpen(StompClient client);

    void onMessage(StompClient client, StompMessage<String> message);

    void onClose(StompClient client, int code);

    void onError(StompClient client, Exception ex);


}
