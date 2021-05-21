package cn.t.util.media.code;

public abstract class TwoDimensionCodeConfig {

    /**
     * width
     */
    private int width = 300;

    /**
     * height
     */
    private int height = 300;

    /**
     * format
     */
    private String format = "png";

    /**
     * charset
     */
    private String charset = "UTF-8";


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }


}
