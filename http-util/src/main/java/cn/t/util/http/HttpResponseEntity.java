package cn.t.util.http;

import org.apache.http.Header;

import java.net.URI;
import java.util.Arrays;

public class HttpResponseEntity {

    private int code;

    private Header[] headers;

    private byte[] content;

    private URI uri;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Header getHeader(final String name) {
        if(headers == null || headers.length == 0) {
            return null;
        }
        for (Header header : headers) {
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "HttpResponseEntity{" +
            "code=" + code +
            ", headers=" + Arrays.toString(headers) +
            ", content=" + (content == null ? "null" : content.length + "B") +
            ", uri=" + uri +
            '}';
    }
}
