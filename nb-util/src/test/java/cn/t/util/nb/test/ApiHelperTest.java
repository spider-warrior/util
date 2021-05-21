package cn.t.util.nb.test;

import cn.t.util.nb.ApiHelper;
import cn.t.util.nb.Configuration;
import cn.t.util.nb.Constant;
import cn.t.util.nb.entity.*;
import cn.t.util.nb.entity.pager.DeviceDataHistoryPager;
import cn.t.util.nb.entity.pager.DevicePager;
import cn.t.util.nb.entity.pagination.PaginationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiHelperTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiHelperTest.class);

    private ApiHelper apiHelper;

    @Before
    public void init() {
        Configuration configuration = new Configuration("72utcMWSQBoGiDmHEscYsuY2rcoa", "CoQjoM8DS5j_G0F5bgFUkb0wzPUa");
        configuration.setKeyStorePath("/home/amen/workspace/idea/my/util/nb-util/src/main/resources/keys/outgoing.CertwithKey.pkcs12");
        configuration.setKeyStorePassword("IoM@1234");
        configuration.setTrustStorePath("/home/amen/workspace/idea/my/util/nb-util/src/main/resources/keys/ca.jks");
        configuration.setTrustStorePassword("Huawei@123");
        apiHelper = new ApiHelper(configuration);
    }

    @Test
    public void requestTokenTest() {
        AccessToken accessToken = apiHelper.requestAccessToken();
        logger.info("token: {}", accessToken.getAccessToken());
    }

    @Test
    public void refreshTokenTest() {
        AccessToken accessToken = apiHelper.requestAccessToken();
        logger.info("first token: {}", accessToken.getAccessToken());
        accessToken = apiHelper.refreshAccessToken(accessToken.getRefreshToken());
        logger.info("second token: {}", accessToken.getAccessToken());
    }

    @Test
    public void queryDeviceCapabilityTest() {
        String deviceId = "e200875f-a239-41c7-a431-eca89f9537c1";
        String gateWayId = "e200875f-a239-41c7-a431-eca89f9537c1";
        AccessToken accessToken = apiHelper.requestAccessToken();
        logger.info("token: {}", accessToken.getAccessToken());
        QueryDeviceCapabilityResponse queryDeviceCapabilityResponse = apiHelper.queryDeviceCapability(accessToken.getAccessToken(), deviceId, gateWayId);
        logger.info("result:\n {}", queryDeviceCapabilityResponse);
    }

    @Test
    public void queryDeviceDataTest() {
        String deviceId = "2e66b07a-f4bd-495c-97ac-dd8969284796";
        AccessToken accessToken = apiHelper.requestAccessToken();
        Device device = apiHelper.queryDeviceData(accessToken.getAccessToken(), deviceId);
        logger.info("result:\n {}", device);
    }

    @Test
    public void queryDeviceHistoryDataTest() {
        String deviceId = "2e66b07a-f4bd-495c-97ac-dd8969284796";
        String gateWayId = "2e66b07a-f4bd-495c-97ac-dd8969284796";
        AccessToken accessToken = apiHelper.requestAccessToken();
        DeviceDataHistoryPager deviceDataHistoryPager = apiHelper.queryDeviceHistoryData(accessToken.getAccessToken(), deviceId, gateWayId);
        logger.info("result:\n {}", deviceDataHistoryPager);
    }

    @Test
    public void queryDevicesTest() {
        String pageNo = "0";
        String pageSize = "5";
        AccessToken accessToken = apiHelper.requestAccessToken();
        DevicePager devicePager = apiHelper.queryDevices(accessToken.getAccessToken(), pageNo, pageSize);
        logger.info("result:\n {}", devicePager);
    }

    @Test
    public void subscribeTopicTest() {
        String callbackUrl = "http://119.61.27.172:18080/callback/device_added";
        AccessToken accessToken = apiHelper.requestAccessToken();
        boolean success = apiHelper.subscribeTopic(accessToken.getAccessToken(), Constant.DEVICE_ADDED, callbackUrl);
        logger.info("result:\n {}", success);
    }

    @Test
    public void deleteDirectlyConnectedDeviceTest() {
        String deviceId = "b366c377-db58-487a-bcd3-d680deb83c50";
        AccessToken accessToken = apiHelper.requestAccessToken();
        boolean success = apiHelper.deleteDirectlyConnectedDevice(accessToken.getAccessToken(), deviceId);
        logger.info("result:\n {}", success);
    }

    @Test
    public void sendDeviceCommandTest() {
        String deviceId = "e200875f-a239-41c7-a431-eca89f9537c1";
        String serviceId = "Alarm";
        String mode = "ACK";
        String method = "CLEAR_ALARM";
        String toType = "GATEWAY";
        String callBackUrl = "http://119.61.27.172:18080/callback/discover_device";
        AccessToken accessToken = apiHelper.requestAccessToken();
        boolean success = apiHelper.sendDeviceCommand(accessToken.getAccessToken(), deviceId, serviceId, mode, method, toType, callBackUrl);
        logger.info("result:\n {}", success);
    }


    @Test
    public void modifyDeviceInfoTest() throws IOException {
        String deviceId = "8324979c-4194-455a-a9e9-107759c1c02e";
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setBatteryLevel("u_1");
        deviceInfo.setBridgeId("u_1");
        deviceInfo.setDescription("u_1");
        deviceInfo.setDeviceType("u_1");
        deviceInfo.setFwVersion("u_1");
        deviceInfo.setHwVersion("u_1");
        deviceInfo.setIsSecurity("true");
        deviceInfo.setLocation("zhangjiazhuang");
        deviceInfo.setMac("u_1");
        deviceInfo.setManufacturerId("u_1");
        deviceInfo.setManufacturerName("u_1");
        deviceInfo.setModel("u_1");
        AccessToken accessToken = apiHelper.requestAccessToken();
        boolean success = apiHelper.modifyDeviceInfo(accessToken.getAccessToken(), deviceId, deviceInfo);
        logger.info("result:\n {}", success);
    }

    @Test
    public void queryDeviceActivationStatusTest() {
        String deviceId = "8324979c-4194-455a-a9e9-107759c1c02e";
        AccessToken accessToken = apiHelper.requestAccessToken();
        DeviceActivationStatus activationStatus = apiHelper.queryDeviceActivationStatus(accessToken.getAccessToken(), deviceId);
        logger.info("result:\n {}", activationStatus);
    }

    @Test
    public void registerDirectlyConnectedDeviceTest() {
        String verifyCode = "xxxxxxxxx9";
        String nodeId = "xxxxxxxx7";
        int timeout = 2;
        AccessToken accessToken = apiHelper.requestAccessToken();
        RegisterDeviceResponse registerDeviceResponse = apiHelper.registerDirectlyConnectedDevice(accessToken.getAccessToken(), verifyCode, nodeId, timeout);
        logger.info("result:\n {}", registerDeviceResponse);
    }

    @Test
    public void cancelDeviceTaskTest() {
        String deviceId = "8324979c-4194-455a-a9e9-107759c1c02e";
        AccessToken accessToken = apiHelper.requestAccessToken();
        CancelDeviceTaskResponse response = apiHelper.cancelDeviceTaskV4(accessToken.getAccessToken(), deviceId);
        logger.info("result:\n {}", response);
    }

    @Test
    public void sendAsyncCommandV4() {
        String deviceId = "e200875f-a239-41c7-a431-eca89f9537c1";
        String serviceId = "Alarm";
        String method = "CLEAR_ALARM";
        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("name", "this is x");
        String callBackUrl = "http://119.61.27.172:18080/callback/discover_device";
        AccessToken accessToken = apiHelper.requestAccessToken();
        AsyncCommandV4 response = apiHelper.sendAsyncCommandV4(accessToken.getAccessToken(), deviceId, serviceId, method, commandParams, callBackUrl);
        logger.info("result:\n {}", response);
    }

    @Test
    public void queryAsyncCommandsV4Test() {
        AccessToken accessToken = apiHelper.requestAccessToken();
        PaginationResponse<AsyncCommandV4> response = apiHelper.queryAsyncCommandsV4(accessToken.getAccessToken());
        logger.info("result:\n {}", response);
    }

    @Test
    public void queryCanceledDeviceCmdV4TaskTest() {
        AccessToken accessToken = apiHelper.requestAccessToken();
        PaginationResponse<CommandTask> response = apiHelper.queryCanceledDeviceCmdV4Task(accessToken.getAccessToken());
        logger.info("result:\n {}", response);
    }

    @Test
    public void updateAsyncCommandV4Test() {
        String commandId = "a049b4b8434e498d921f81ec25849f5d";
        AccessToken accessToken = apiHelper.requestAccessToken();
        Object response = apiHelper.updateAsyncCommandV4(accessToken.getAccessToken(), commandId);
        logger.info("result:\n {}", response);
    }


    @After
    public void destroy() {

    }
}
