package cn.t.util.socket.test.shouwangliming.action;

import cn.t.util.common.JsonUtil;
import cn.t.util.socket.test.shouwangliming.Cmd;
import cn.t.util.socket.test.shouwangliming.WebSocketMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.java_websocket.client.WebSocketClient;

import java.util.HashMap;
import java.util.Map;

public class AllianceMsgAction {
    public void sendMsg(WebSocketClient socketClient) {
        Map<String, Object> countryParams = new HashMap<>();
        countryParams.put("msg", "whoops...");
        countryParams.put("roomId", "alliance_481_1930a359d0284a559e5d8b1d68fb1c29");
        try {
            socketClient.send(JsonUtil.serialize(new WebSocketMsg(Cmd.CHAT_ROOM, countryParams)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
