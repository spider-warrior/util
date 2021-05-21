package cn.t.util.validator;

import java.util.StringJoiner;

/**
 * 属性错误
 *
 * @author yj
 * @since 2020-03-05 16:54
 **/
public class FieldError {
    private String path;
    private String msg;

    public FieldError(String path, String msg) {
        this.path = path;
        this.msg = msg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FieldError.class.getSimpleName() + "[", "]")
            .add("path='" + path + "'")
            .add("msg='" + msg + "'")
            .toString();
    }
}
