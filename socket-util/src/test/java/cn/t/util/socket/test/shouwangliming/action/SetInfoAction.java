package cn.t.util.socket.test.shouwangliming.action;

import cn.t.util.common.JsonUtil;
import cn.t.util.socket.test.shouwangliming.Cmd;
import cn.t.util.socket.test.shouwangliming.WebSocketMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.java_websocket.client.WebSocketClient;

import java.util.HashMap;
import java.util.Map;

public class SetInfoAction {

    public void sendMsg(WebSocketClient socketClient) {
        Map<String, Object> allianceParams = new HashMap<>();
//        allianceParams.put("lastUpdateTime", System.currentTimeMillis());
        allianceParams.put("lastUpdateTime", 1564994884);
        allianceParams.put("userName", "whateverU");
        try {
            socketClient.send(JsonUtil.serialize(new WebSocketMsg(Cmd.ROOM_JOIN, allianceParams)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
