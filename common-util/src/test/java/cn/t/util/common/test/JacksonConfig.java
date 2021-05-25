//package cn.t.util.common.test;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
//import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author xiaolong
// * @description 返回json空值, 去掉null和“”
// */
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
//        return objectMapper;
//    }
//
//    /**
//     * 处理数组类型的null值
//     */
//    public static class NullArrayJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
//            if (value == null) {
//                jgen.writeStartArray();
//                jgen.writeEndArray();
//            }
//        }
//    }
//
//    /**
//     * 处理bollean等类型的null值
//     */
//    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//            jsonGenerator.writeBoolean(false);
//        }
//    }
//
//    /**
//     * 处理字符串等类型的null值
//     */
//    public static class NullStringJsonSerializer extends JsonSerializer<Object> {
//        @Override
//        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//            jsonGenerator.writeString(StringUtils.EMPTY);
//        }
//    }
//
//    public static class MyBeanSerializerModifier extends BeanSerializerModifier {
//
//        @Override
//        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
//            //循环所有的beanPropertyWriter
//            for (Object beanProperty : beanProperties) {
//                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
//                //判断字段的类型，如果是array，list，set则注册nullSerializer
//                if (isArrayType(writer)) {
//                    //给writer注册一个自己的nullSerializer
//                    writer.assignNullSerializer(new NullArrayJsonSerializer());
//                }else if (isBooleanType(writer)) {
//                    writer.assignNullSerializer(new NullBooleanJsonSerializer());
//                } else if (isStringType(writer)) {
//                    writer.assignNullSerializer(new NullStringJsonSerializer());
//                }
//            }
//            return beanProperties;
//        }
//
//        /**
//         * 是否是数组
//         */
//        private boolean isArrayType(BeanPropertyWriter writer) {
//            Class<?> clazz = writer.getType().getRawClass();
//            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
//        }
//
//        /**
//         * 是否是string
//         */
//        private boolean isStringType(BeanPropertyWriter writer) {
//            Class<?> clazz = writer.getType().getRawClass();
//            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
//        }
//
//        /**
//         * 是否是boolean
//         */
//        private boolean isBooleanType(BeanPropertyWriter writer) {
//            Class<?> clazz = writer.getType().getRawClass();
//            return clazz.equals(Boolean.class);
//        }
//
//    }
//}
