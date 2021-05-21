package cn.t.util.socket.messaging;

import cn.t.util.socket.messaging.stomp.SimpMessageType;
import cn.t.util.socket.messaging.stomp.StompCommand;

import java.util.Map;

public class SimpMessageHeaderAccessor {

    // SiMP header names
    public static final String DESTINATION_HEADER = "simpDestination";
    public static final String MESSAGE_TYPE_HEADER = "simpMessageType";
    public static final String SESSION_ID_HEADER = "simpSessionId";
    public static final String SESSION_ATTRIBUTES = "simpSessionAttributes";
    public static final String SUBSCRIPTION_ID_HEADER = "simpSubscriptionId";
    public static final String USER_HEADER = "simpUser";
    public static final String CONNECT_MESSAGE_HEADER = "simpConnectMessage";
    public static final String DISCONNECT_MESSAGE_HEADER = "simpDisconnectMessage";
    public static final String HEART_BEAT_HEADER = "simpHeartbeat";


    // Other header names

    public static final String COMMAND_HEADER = "stompCommand";

    public static final String CREDENTIALS_HEADER = "stompCredentials";

    /**
     * A header for internal use with "user" destinations where we need to
     * restore the destination prior to sending messages to clients.
     */
    public static final String ORIGINAL_DESTINATION = "simpOrigDestination";

    /**
     * A header that indicates to the broker that the sender will ignore errors.
     * The header is simply checked for presence or absence.
     */
    public static final String IGNORE_ERROR = "simpIgnoreError";

    public static SimpMessageType getMessageType(Map<String, Object> headers) {
        return (SimpMessageType) getHeader(headers, MESSAGE_TYPE_HEADER);
    }

    public static StompCommand getCommand(Map<String, Object> headers) {
        return (StompCommand) getHeader(headers, COMMAND_HEADER);
    }

    public static Object getHeader(Map<String, Object> headers, String headerName) {
        return headers.get(headerName);
    }

}
