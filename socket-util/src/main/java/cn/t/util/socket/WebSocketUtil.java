package cn.t.util.socket;

import cn.t.util.socket.messaging.stomp.StompClient;
import cn.t.util.socket.messaging.stomp.StompEventHandler;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebSocketUtil {

    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA-1";
    private static final byte[] WS_ACCEPT = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11".getBytes(StandardCharsets.ISO_8859_1);
    private static final Map<String, Queue<MessageDigest>> queues = new HashMap<>();

    static {
        try {
            // Init commonly used algorithms
            init(MD5);
            init(SHA1);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static StompClient createStompClient(URI serverUri, Map<String, String> httpHeaders, StompEventHandler stompEventHandler) {
        return new StompClient(serverUri, httpHeaders, stompEventHandler);
    }

    public static String generateWebSocketAccept(String key) {
        byte[][] input = {key.getBytes(StandardCharsets.ISO_8859_1), WS_ACCEPT};
        Queue<MessageDigest> queue = queues.get(SHA1);
        if (queue == null) {
            throw new IllegalStateException("Must call init() first");
        }
        MessageDigest md = queue.poll();
        if (md == null) {
            try {
                md = MessageDigest.getInstance(SHA1);
            } catch (NoSuchAlgorithmException e) {
                // Ignore. Impossible if init() has been successfully called
                // first.
                throw new IllegalStateException("Must call init() first");
            }
        }
        for (byte[] bytes : input) {
            md.update(bytes);
        }
        byte[] result = md.digest();
        return Base64.getEncoder().encodeToString(result);
    }

    private static void init(String algorithm) throws NoSuchAlgorithmException {
        synchronized (queues) {
            if (!queues.containsKey(algorithm)) {
                MessageDigest md = MessageDigest.getInstance(algorithm);
                Queue<MessageDigest> queue = new ConcurrentLinkedQueue<>();
                queue.add(md);
                queues.put(algorithm, queue);
            }
        }
    }
}
