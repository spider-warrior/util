package cn.t.util.socket.test.shouwangliming;

import cn.t.util.common.schedule.TaskUtil;
import cn.t.util.socket.test.shouwangliming.action.*;
import org.java_websocket.client.WebSocketClient;

public class WebSocketLoginSuccessProcess {

    private WebSocketClient socketClient;
    public void process() {
        SetInfoAction setInfoAction = new SetInfoAction();
        setInfoAction.sendMsg(socketClient);

        SetDeviceAction setDeviceAction = new SetDeviceAction();
        setDeviceAction.sendMsg(socketClient);

        JoinCountryChatRoomAction joinCountryChatRoomAction = new JoinCountryChatRoomAction();
        joinCountryChatRoomAction.sendMsg(socketClient);

        JoinAllianceChatRoomAction joinAllianceChatRoomAction = new JoinAllianceChatRoomAction();
        joinAllianceChatRoomAction.sendMsg(socketClient);

        AllianceMsgAction allianceMsgAction = new AllianceMsgAction();
        TaskUtil.createFixedTimerTask(50000, 0, () -> allianceMsgAction.sendMsg(socketClient));

//        CountryMsgAction countryMsgAction = new CountryMsgAction();
//        TaskUtil.createFixedTimerTask(5000, 0, () -> countryMsgAction.sendMsg(socketClient));

    }

    public WebSocketLoginSuccessProcess(WebSocketClient socketClient) {
        this.socketClient = socketClient;
    }
}
