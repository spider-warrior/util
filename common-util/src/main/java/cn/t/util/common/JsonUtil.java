package cn.t.util.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
        .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .build();


    public static <T> T deserialize(String data, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(data, clazz);
    }

    public static <T> T deserialize(String data, TypeReference<T> tTypeReference) throws IOException {
        return OBJECT_MAPPER.readValue(data, tTypeReference);
    }

    public static String serialize(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
}
