package cn.t.util.socket.messaging;

import java.util.Map;

public interface Message<T> {

    T getPayload();

    Map<String, Object> getHeaders();

}
