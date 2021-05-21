package cn.t.util.socket.messaging.stomp;

import cn.t.util.socket.messaging.Message;

import java.util.Map;

public class StompMessage<T> implements Message<T> {

    private final T payload;

    private final Map<String, Object> headers;

    public StompMessage(T payload, Map<String, Object> headers) {
        this.payload = payload;
        this.headers = headers;
    }

    @Override
    public T getPayload() {
        return payload;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append(" [payload=");
        if (this.payload instanceof byte[]) {
            sb.append("byte[").append(((byte[]) this.payload).length).append("]");
        } else {
            sb.append(this.payload);
        }
        sb.append(", headers=").append(this.headers).append("]");
        return sb.toString();
    }
}
