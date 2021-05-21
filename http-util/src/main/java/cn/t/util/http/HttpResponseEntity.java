package cn.t.util.http;

import org.apache.http.Header;

import java.util.Arrays;

public class HttpResponseEntity {

    private int code;

    private String contentType;

    private Header[] headers;

    private Object content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResponseEntity{" +
            "code=" + code +
            ", contentType='" + contentType + '\'' +
            ", headers=" + Arrays.toString(headers) +
            ", content=" + (content instanceof byte[] ? Arrays.toString((byte[])content) : content) +
            '}';
    }
}
