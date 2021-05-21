package cn.t.util.common.test;

import cn.t.util.common.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtilTest {

    @Test
    public void testArray() throws IOException {
        String str = "[\"aaa\",\"bbb\",\"ccc\"]";
        List list = JsonUtil.deserialize(str, List.class);
        System.out.println(list);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMap() throws IOException {
        String json = "{\"status\": 1,\"message\": \"\",\"errorCode\": null,\"field\": \"ok\",\"data\": {\"aggregate\": {\"319\": {\"building_malfunction_count\": 4,\"building_alarm_count\": 1,\"building_emergency_alarm_count\": 1,\"building_trouble_count\": 3,\"longitude\": 22.815612,\"latitude\": 108.363609},\"324\": {\"building_trouble_count\": 2,\"longitude\": 22.811681,\"latitude\": 108.350026}}}}";
        HashMap result = JsonUtil.deserialize(json, HashMap.class);
        System.out.println(result.getClass());
        Map<String, Object> data = (Map<String, Object>) result.get("data");
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            System.out.println(entry.getKey());
            Map<String, Object> aggregate = (Map<String, Object>) entry.getValue();
            for (Map.Entry<String, Object> e : aggregate.entrySet()) {
                System.out.println("key: " + e.getKey());
                System.out.println("value: " + e.getValue());
            }
        }
    }

    @Test
    public void deserializeTest() throws IOException {
        Student student = new Student();
        student.setId(1L);
        student.setName("小明");
        Result<Student> studentResult = new Result<>();
        studentResult.setCode("200");
        studentResult.setBody(student);
        String json = JsonUtil.serialize(studentResult);
        System.out.println(json);
        studentResult = JsonUtil.deserialize(json, new TypeReference<Result<Student>>() {});
        System.out.println(studentResult);
    }

}


class Result<T> {
    private String code;
    private T body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Result{" +
            "code='" + code + '\'' +
            ", body=" + body +
            '}';
    }
}

class Student {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
