package cn.t.util.media.video.mp4.modal;

public abstract class Box {

    /**
     * size
     */
    protected int size;

    /**
     * type
     */
    protected String type;

    /**
     * largeSize
     */
    private long largeSize;

    /**
     * user type
     */
    private String userType;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getLargeSize() {
        return largeSize;
    }

    public void setLargeSize(long largeSize) {
        this.largeSize = largeSize;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
