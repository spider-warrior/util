package cn.t.util.socket.test.shouwangliming.action;

import cn.t.util.common.JsonUtil;
import cn.t.util.socket.test.shouwangliming.Cmd;
import cn.t.util.socket.test.shouwangliming.WebSocketMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.java_websocket.client.WebSocketClient;

import java.util.HashMap;
import java.util.Map;

public class SetDeviceAction {
    public void sendMsg(WebSocketClient socketClient) {
        Map<String, Object> allianceParams = new HashMap<>();
        allianceParams.put("systemVerson", "12.2");
        allianceParams.put("deviceName", "iPhone 7 Plus");
        allianceParams.put("appName", "Last Shelter:Survival");
        allianceParams.put("serverNum", "481");
        allianceParams.put("appVersion", "1.250.133");
        allianceParams.put("deviceType", "iphone");
        try {
            socketClient.send(JsonUtil.serialize(new WebSocketMsg(Cmd.USER_SETDEVICE, allianceParams)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
