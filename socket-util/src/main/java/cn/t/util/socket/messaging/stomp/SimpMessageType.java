package cn.t.util.socket.messaging.stomp;

public enum SimpMessageType {

    CONNECT("CONNECT"),

    CONNECT_ACK("CONNECT_ACK"),

    SEND("SEND"),

    MESSAGE("MESSAGE"),

    SUBSCRIBE("SUBSCRIBE"),

    UNSUBSCRIBE("UNSUBSCRIBE"),

    HEARTBEAT("HEARTBEAT"),

    DISCONNECT("DISCONNECT"),

    DISCONNECT_ACK("DISCONNECT_ACK"),

    OTHER("OTHER");

    private final String value;

    SimpMessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
