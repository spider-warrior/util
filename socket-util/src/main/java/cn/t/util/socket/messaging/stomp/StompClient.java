package cn.t.util.socket.messaging.stomp;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.JsonUtil;
import cn.t.util.common.schedule.TaskUtil;
import cn.t.util.socket.messaging.convert.DefaultConvert;
import cn.t.util.socket.messaging.convert.InstanceConvert;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class StompClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(StompClient.class);

    private static final byte CONNECTING = 0;
    private static final byte OPEN = 1;
    private static final byte CLOSING = 2;
    private static final byte CLOSED = 3;

    //\u0000
    private static final byte[] END = {92, 117, 48, 48, 48, 48};
    private static final byte[] LF = {92, 110};
    private static final byte COLON = 58;
    private static final byte DOUBLE_QUOTATION = 34;
    private static final byte LEFT_BRACKET = 91;
    private static final byte RIGHT_BRACKET = 93;
    private final DefaultConvert defaultConvert = new DefaultConvert();
    private final StompEventHandler stompEventHandler;
    private List<InstanceConvert> instanceConvertList = new ArrayList<>();
    private int subIndex = 0;
    private byte readyState = CONNECTING;
    private Timer connectionTimeout;

    public StompClient(URI serverUri, Map<String, String> httpHeaders, StompEventHandler stompEventHandler) {
        super(serverUri, httpHeaders);
        this.stompEventHandler = stompEventHandler;
    }

    @Override
    public void connect() {
        connectionTimeout = TaskUtil.createFixedTimerTask(15000, 15000, () -> close(2007, "Transport timed out"));
        super.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.debug("on open....");
    }

    @Override
    public void onMessage(String message) {
        logger.debug("on message: {}", message);
        String type = message.substring(0, 1);
        String content = message.substring(1);
        //此为socket中的协议 o --> open, h --> heart beat, a --> array, m --> message, c --> close
        switch (type) {
            case "o": {
                _open();
                break;
            }
            case "h": {
                _heartBeat();
                break;
            }
            case "c": {
                _close(content);
                break;
            }
            case "a": {
                _onMessage(true, content);
                break;
            }
            case "m": {
                _onMessage(false, content);
                break;
            }
            default: {
                logger.warn("unknown message: {}", message);
            }
        }
    }

    private void _open() {
        if (this.readyState == CONNECTING) {
            if (connectionTimeout != null) {
                connectionTimeout.cancel();
            }
            this.readyState = OPEN;
            stompEventHandler.onOpen(this);
        } else {
            close(1006, "Server lost session");
        }
    }

    private void _close(String content) {
    }

    @SuppressWarnings("unchecked")
    private void _onMessage(boolean isArray, String content) {
        if (!isArray) {
            stompEventHandler.onMessage(this, buildStompMessage(content));
        } else {
            try {
                List<String> list = (List<String>) JsonUtil.deserialize(content, List.class);
                for (String str : list) {
                    stompEventHandler.onMessage(this, buildStompMessage(str));
                }
            } catch (IOException e) {
                logger.error("", e);
            }
        }

    }

    private void _heartBeat() {
    }

    private StompMessage<String> buildStompMessage(String content) {
        String[] entry = content.split("\\n\\n");
        String[] headerEntries = entry[0].split("\\n");
        Map<String, Object> header = new HashMap<>();
        for (int i = 1; i < headerEntries.length; i++) {
            String[] headerEntry = headerEntries[i].split(":");
            header.put(headerEntry[0], headerEntry[1]);
        }
        return new StompMessage<>(entry[1], header);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.debug("on close, code: {}, reason: {}, remote: {}", code, reason, reason);
        stompEventHandler.onClose(this, code);
    }

    @Override
    public void onError(Exception ex) {
        logger.debug("on exception: {}", ex.getMessage());
        stompEventHandler.onError(this, ex);
    }

    /**
     * subscribe
     * @param path xxx
     * @throws IOException xxx
     */
    public void subscribe(String path) throws IOException {
        Map<String, Object> headers = new HashMap<>();
        headers.put(StompHeaders.STOMP_ID_HEADER, "sub-" + (subIndex++));
        headers.put(StompHeaders.STOMP_DESTINATION_HEADER, path);
        StompMessage<String> message = new StompMessage<>(null, headers);
        sendCommand(StompCommand.SUBSCRIBE, message);
    }

    /**
     * send
     * @param path    xxx
     * @param content xxx
     * @throws IOException xxx
     */
    public void send(String path, String content) throws IOException {
        Map<String, Object> headers = new HashMap<>();
        headers.put(StompHeaders.STOMP_DESTINATION_HEADER, path);
        headers.put(StompHeaders.STOMP_CONTENT_LENGTH_HEADER, content == null ? 0 : content.length());
        StompMessage<String> message = new StompMessage<>(content, headers);
        sendCommand(StompCommand.SEND, message);
    }

    private void sendCommand(StompCommand stompCommand, StompMessage<?> t) throws IOException {
        checkMessage(stompCommand, t);
        byte[] payload = buildMessageBytes(stompCommand, t);
        send(new String(payload));
    }

    private byte[] buildMessageBytes(StompCommand stompCommand, StompMessage<?> t) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        baos.write(LEFT_BRACKET);
        baos.write(DOUBLE_QUOTATION);
        //Command
        baos.write(stompCommand.getMessageType().getValue().getBytes());
        baos.write(LF);
        //headers
        Map<String, Object> headers = t.getHeaders();
        if (!CollectionUtil.isEmpty(headers)) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                baos.write(entry.getKey().getBytes());
                baos.write(COLON);
                baos.write(selectInstanceConvert(entry.getValue()).convertToBytes(entry.getValue()));
                baos.write(LF);
            }
        }
        baos.write(LF);
        Object payload = t.getPayload();
        if (payload != null) {
            baos.write(selectInstanceConvert(payload).convertToBytes(payload));
        }
        baos.write(END);
        baos.write(DOUBLE_QUOTATION);
        baos.write(RIGHT_BRACKET);
        return baos.toByteArray();
    }

    private void checkMessage(StompCommand stompCommand, StompMessage<?> t) {
        SimpMessageType simpMessageType = stompCommand.getMessageType();
        if (simpMessageType == null) {
            throw new IllegalArgumentException("simpMessageType cannot be null");
        }
        //destination
        if (stompCommand.requiresDestination() && t.getHeaders().get(StompHeaders.STOMP_DESTINATION_HEADER) == null) {
            throw new IllegalArgumentException("header " + StompHeaders.STOMP_DESTINATION_HEADER + " missing");
        } else if (stompCommand.requiresContentLength() && t.getHeaders().get(StompHeaders.STOMP_CONTENT_LENGTH_HEADER) == null) {
            throw new IllegalArgumentException("header " + StompHeaders.STOMP_CONTENT_LENGTH_HEADER + " missing");
        }
    }

    private InstanceConvert selectInstanceConvert(Object obj) {
        for (InstanceConvert convert : instanceConvertList) {
            if (convert.support(obj)) {
                return convert;
            }
        }
        return defaultConvert;
    }

    public List<InstanceConvert> getInstanceConvertList() {
        return instanceConvertList;
    }

    public void setInstanceConvertList(List<InstanceConvert> instanceConvertList) {
        if (instanceConvertList != null && instanceConvertList.size() > 0) {
            this.instanceConvertList.addAll(instanceConvertList);
        }
    }

}
