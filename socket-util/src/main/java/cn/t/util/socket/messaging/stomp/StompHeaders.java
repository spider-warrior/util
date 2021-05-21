package cn.t.util.socket.messaging.stomp;

public final class StompHeaders {

    public static final String STOMP_ID_HEADER = "id";

    public static final String STOMP_HOST_HEADER = "host";

    public static final String STOMP_ACCEPT_VERSION_HEADER = "accept.version";

    public static final String STOMP_MESSAGE_ID_HEADER = "message-id";

    public static final String STOMP_RECEIPT_HEADER = "receipt"; // any client frame except CONNECT

    public static final String STOMP_RECEIPT_ID_HEADER = "receipt-id"; // RECEIPT frame

    public static final String STOMP_SUBSCRIPTION_HEADER = "subscription";

    public static final String STOMP_VERSION_HEADER = "version";

    public static final String STOMP_MESSAGE_HEADER = "message";

    public static final String STOMP_ACK_HEADER = "ack";

    public static final String STOMP_NACK_HEADER = "nack";

    public static final String STOMP_LOGIN_HEADER = "login";

    public static final String STOMP_PASSCODE_HEADER = "passcode";

    public static final String STOMP_DESTINATION_HEADER = "destination";

    public static final String STOMP_CONTENT_TYPE_HEADER = "content-type";

    public static final String STOMP_CONTENT_LENGTH_HEADER = "content-length";

    public static final String STOMP_HEARTBEAT_HEADER = "heart-beat";

    // native headers
    public static final String NATIVE_HEADERS = "nativeHeaders";

    private StompHeaders() {
    }
}
