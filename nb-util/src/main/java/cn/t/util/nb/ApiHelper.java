package cn.t.util.nb;

import cn.t.util.common.JsonUtil;
import cn.t.util.http.*;
import cn.t.util.nb.entity.*;
import cn.t.util.nb.entity.pager.DeviceDataHistoryPager;
import cn.t.util.nb.entity.pager.DevicePager;
import cn.t.util.nb.entity.pagination.PaginationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class ApiHelper {

    private static final Logger logger = LoggerFactory.getLogger(ApiHelper.class);

    private Configuration configuration;

    public ApiHelper(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 获取access token
     * @return xxx
     */
    public AccessToken requestAccessToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("appId", configuration.getAppKey());
        params.put("secret", configuration.getAppSecret());
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.APP_AUTH,
                params,
                ParamFormat.URL_FORM_ENCODE,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeAccessToken(Constant.APP_AUTH, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 刷新access token
     * @param refreshToken xxx
     * @return xxx
     */
    public AccessToken refreshAccessToken(String refreshToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("appId", configuration.getAppKey());
        params.put("secret", configuration.getAppSecret());
        params.put("refreshToken", refreshToken);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.REFRESH_TOKEN,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeAccessToken(Constant.REFRESH_TOKEN, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询设备能力
     * @param accessToken xxx
     * @param deviceId xxx
     * @param gatewayId xxx
     * @return xxx
     */
    public QueryDeviceCapabilityResponse queryDeviceCapability(String accessToken, String deviceId, String gatewayId) {
        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);
        params.put("gatewayId", gatewayId);

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICE_CAPABILITIES,
                headers,
                params,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeQueryDeviceCapabilityResponse(Constant.QUERY_DEVICE_CAPABILITIES, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询设备数据
     * @param accessToken xxx
     * @param deviceId xxx
     * @return xxx
     */
    public Device queryDeviceData(String accessToken, String deviceId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICE_DATA + "/" + deviceId,
                headers,
                null,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeDevice(Constant.QUERY_DEVICE_DATA + "/" + deviceId, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询设备历史数据
     *
     * @param accessToken xxx
     * @param deviceId xxx
     * @param gatewayId xxx
     * @return xxx
     */
    public DeviceDataHistoryPager queryDeviceHistoryData(String accessToken, String deviceId, String gatewayId) {
        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);
        params.put("gatewayId", gatewayId);

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICE_HISTORY_DATA,
                headers,
                params,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeDeviceDataHistoryPager(Constant.QUERY_DEVICE_HISTORY_DATA, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询设备
     *
     * @param accessToken xxx
     * @param pageNo xxx
     * @param pageSize xxx
     * @return xxx
     */
    public DevicePager queryDevices(String accessToken, String pageNo, String pageSize) {

        Map<String, Object> params = new HashMap<>();
        params.put("appId", configuration.getAppKey());
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICES,
                headers,
                params,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeDevicePager(Constant.QUERY_DEVICES, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 订阅
     *
     * @param accessToken xxx
     * @param notifyType xxx
     * @param callbackUrl xxx
     * @return xxx
     */
    public boolean subscribeTopic(String accessToken, String notifyType, String callbackUrl) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("notifyType", notifyType);
        params.put("callbackurl", callbackUrl);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.SUBSCRIBE_NOTIFYCATION,
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return isSuccess(entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 删除直连设备
     *
     * @param accessToken xxx
     * @param deviceId xxx
     * @return xxx
     */
    public boolean deleteDirectlyConnectedDevice(String accessToken, String deviceId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslDeleteWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.DELETE_DEVICE + "/" +deviceId,
                headers,
                null,
                keyManagerFactory,
                trustManagerFactory);
            return isSuccess(entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 发现非直连设备
     *
     * @param accessToken xxx
     * @param deviceId xxx
     * @param serviceId xxx
     * @param mode xxx
     * @param method xxx
     * @param toType xxx
     * @param callbackURL xxx
     * @return xxx
     */
    public boolean sendDeviceCommand(String accessToken, String deviceId, String serviceId, String mode, String method, String toType, String callbackURL) {

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("mode", mode);
        commandParams.put("method", method);
        commandParams.put("toType", toType);
        commandParams.put("callbackURL", callbackURL);

        Map<String, String> commandBody = new HashMap<>();
        commandBody.put("cmdBody", "discover indirect device cmd body content.");

        Map<String, Object> params = new HashMap<>();
        params.put("header", commandParams);
        params.put("body", commandBody);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                String.format(Constant.DISCOVER_INDIRECT_DEVICE, deviceId, serviceId),
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return isSuccess(entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 修改DeviceInfo
     * 此时json解析deviceInfo的结果不包含空值的字段
     *
     * @param accessToken xxx
     * @param deviceId xxx
     * @param deviceInfo xxx
     * @return xxx
     * @throws IOException xxx
     */
    @SuppressWarnings("unchecked")
    public boolean modifyDeviceInfo(String accessToken, String deviceId, DeviceInfo deviceInfo) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);
        Map map = JsonUtil.deserialize(JsonUtil.serialize(deviceInfo), HashMap.class);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPutWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.MODIFY_DEVICE_INFO + "/" + deviceId,
                headers,
                map,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return isSuccess(entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 查询设备状态
     *
     * @param accessToken xxx
     * @param deviceId xxx
     * @return xxx
     */
    public DeviceActivationStatus queryDeviceActivationStatus(String accessToken, String deviceId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);
        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICE_ACTIVATION_STATUS + "/" + deviceId,
                headers,
                null,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeDeviceActivationStatus(Constant.QUERY_DEVICE_ACTIVATION_STATUS + "/" + deviceId, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 注册设备
     *
     * @param accessToken xxx
     * @param verifyCode xxx
     * @param nodeId xxx
     * @param timeout xxx
     * @return xxx
     */
    public RegisterDeviceResponse registerDirectlyConnectedDevice(String accessToken, String verifyCode, String nodeId, Integer timeout) {

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("verifyCode", verifyCode.toUpperCase());
        params.put("nodeId", nodeId.toUpperCase());
        params.put("timeout", timeout);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.REGISTER_DEVICE,
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeRegisterDeviceResponse(Constant.REGISTER_DEVICE, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public CancelDeviceTaskResponse cancelDeviceTaskV4(String accessToken, String deviceId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.CREATE_DEVICECMD_CANCEL_TASK,
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeCancelDeviceTaskResponse(Constant.CREATE_DEVICECMD_CANCEL_TASK, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 发送异步任务
     *
     * @param accessToken   xxx
     * @param deviceId xxx
     * @param serviceId xxx
     * @param method xxx
     * @param commandParams xxx
     * @param callbackUrl xxx
     * @return xxx
     */
    public AsyncCommandV4 sendAsyncCommandV4(String accessToken, String deviceId, String serviceId, String method, Map<String, ?> commandParams, String callbackUrl) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> commandParamMap = new HashMap<>();
        commandParamMap.put("serviceId", serviceId);
        commandParamMap.put("method", method);
        commandParamMap.put("paras", commandParams);

        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);
        params.put("command", commandParamMap);
        params.put("callbackUrl", callbackUrl);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPostWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.POST_ASYN_CMD,
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeAsyncCommandV4(Constant.POST_ASYN_CMD, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询异步任务
     *
     * @param accessToken xxx
     * @return xxx
     */
    public PaginationResponse<AsyncCommandV4> queryAsyncCommandsV4(String accessToken) {

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICE_CMD,
                headers,
                null,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeAsyncCommandV4Pagination(Constant.QUERY_DEVICE_CMD, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 查询设备取消任务
     *
     * @param accessToken xxx
     * @return xxx
     */
    public PaginationResponse<CommandTask> queryCanceledDeviceCmdV4Task(String accessToken) {

        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslGetWithKeyManagerFactoryAndTrustManagerFactory(
                Constant.QUERY_DEVICECMD_CANCEL_TASK,
                headers,
                null,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeCommandTaskPagination(Constant.QUERY_DEVICECMD_CANCEL_TASK, entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 更新任务
     *
     * @param accessToken xxx
     * @param commandId xxx
     * @return xxx
     */
    public AsyncCommandV4 updateAsyncCommandV4(String accessToken, String commandId) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constant.HEADER_APP_KEY, configuration.getAppKey());
        headers.put(Constant.HEADER_APP_AUTH, Constant.HEADER_APP_AUTH_VALUE_PREFIX + accessToken);

        Map<String, Object> params = new HashMap<>();
        params.put("status", "CANCELED");

        try {
            KeyManagerFactory keyManagerFactory = initKeyManagerFactory();
            TrustManagerFactory trustManagerFactory = initTrustManagerFactory();
            HttpResponseEntity entity = HttpClientUtil.sslPutWithKeyManagerFactoryAndTrustManagerFactory(
                String.format(Constant.UPDATE_ASYN_COMMAND, commandId),
                headers,
                params,
                ParamFormat.APPLICATION_JSON,
                keyManagerFactory,
                trustManagerFactory);
            return deserializeAsyncCommandV4(String.format(Constant.UPDATE_ASYN_COMMAND, commandId), entity);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }


    private boolean isSuccess(HttpResponseEntity entity) {
        if (entity.getCode() < 300) {
            if(entity.getContent() != null && ((String)entity.getContent()).length() > 0) {
                String content = (String)entity.getContent();
                if(content.contains("error_code")) {
                    try {
                        HashMap map = JsonUtil.deserialize(content, HashMap.class);
                        logger.error("error code: {}, details: {}", map.get("error_code"), map.get("error_desc"));
                    } catch (IOException e) {
                        logger.error("ready result body failed", e);
                    }
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private String extractStringBody(String uri, HttpResponseEntity entity) {
        logger.debug("entity: {}", entity);
        if (entity.getCode() >=200 && entity.getCode() < 300) {
            String content = (String)entity.getContent();
            if(content.contains("error_code")) {
                throw new RuntimeException(content);
            }
            return content;
        } else {
            throw new RuntimeException(String.format("请求失败: %s, detail: %s", uri, entity.getContent()));
        }
    }

    private AccessToken deserializeAccessToken(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, AccessToken.class);
    }

    private QueryDeviceCapabilityResponse deserializeQueryDeviceCapabilityResponse(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, QueryDeviceCapabilityResponse.class);
    }

    private Device deserializeDevice(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, Device.class);
    }

    private DeviceDataHistoryPager deserializeDeviceDataHistoryPager(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, DeviceDataHistoryPager.class);
    }

    private DevicePager deserializeDevicePager(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, DevicePager.class);
    }

    private DeviceActivationStatus deserializeDeviceActivationStatus(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, DeviceActivationStatus.class);
    }

    private RegisterDeviceResponse deserializeRegisterDeviceResponse(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, RegisterDeviceResponse.class);
    }

    private CancelDeviceTaskResponse deserializeCancelDeviceTaskResponse(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, CancelDeviceTaskResponse.class);
    }

    private AsyncCommandV4 deserializeAsyncCommandV4(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, AsyncCommandV4.class);
    }

    private PaginationResponse<AsyncCommandV4> deserializeAsyncCommandV4Pagination(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, new TypeReference<PaginationResponse<AsyncCommandV4>>(){});
    }

    private PaginationResponse<CommandTask> deserializeCommandTaskPagination(String uri, HttpResponseEntity entity) throws IOException {
        String body = extractStringBody(uri, entity);
        return JsonUtil.deserialize(body, new TypeReference<PaginationResponse<CommandTask>>(){});
    }


    private KeyManagerFactory initKeyManagerFactory() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, InvalidCertificateType, IOException, UnrecoverableKeyException {
        return KeyStoreUtil.initSunX509KeyManagerFactory(
            KeyStoreUtil.loadCertificate(
                CertificateType.PKCS12,
                configuration.getKeyStorePath(),
                configuration.getKeyStorePassword().toCharArray()),
            configuration.getKeyStorePassword().toCharArray());
    }

    private TrustManagerFactory initTrustManagerFactory() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, InvalidCertificateType, IOException, UnrecoverableKeyException {
        return KeyStoreUtil.initSunX509TrustManagerFactory(
            KeyStoreUtil.loadCertificate(
                CertificateType.JKS,
                configuration.getTrustStorePath(),
                configuration.getTrustStorePassword().toCharArray()));
    }

}
