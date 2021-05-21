package cn.t.util.io.crypto.entity;

import java.util.Arrays;

/**
 * 资源索引
 * */
public class ResourceIndex {

    /**
     * resource-key长度
     * */
    private byte resourceKeyLength;
    /**
     * 资源key
     * */
    private byte[] resourceKeyBytes = new byte[0];
    /**
     * data path length
     * */
    private int dataPathLength;
    /**
     * 记录数据所在位置
     * */
    private byte[] dataPath;
    /**
     * 开始下标
     * */
    private long startIndex;

    /**
     * 结束下标
     * */
    private long endIndex;
    /**
     * 长度
     * */
    private long dataLength;
    /**
     * 创建时间
     * */
    private long crTime;

    public byte getResourceKeyLength() {
        return resourceKeyLength;
    }

    public void setResourceKeyLength(byte resourceKeyLength) {
        this.resourceKeyLength = resourceKeyLength;
    }

    public byte[] getResourceKeyBytes() {
        return resourceKeyBytes;
    }

    public void setResourceKeyBytes(byte[] resourceKeyBytes) {
        this.resourceKeyBytes = resourceKeyBytes;
    }

    public int getDataPathLength() {
        return dataPathLength;
    }

    public void setDataPathLength(int dataPathLength) {
        this.dataPathLength = dataPathLength;
    }

    public byte[] getDataPath() {
        return dataPath;
    }

    public void setDataPath(byte[] dataPath) {
        this.dataPath = dataPath;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(long endIndex) {
        this.endIndex = endIndex;
    }

    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }

    public long getCrTime() {
        return crTime;
    }

    public void setCrTime(long crTime) {
        this.crTime = crTime;
    }

    @Override
    public String toString() {
        return "ResourceIndex{" +
            "resourceKeyLength=" + resourceKeyLength +
            ", resourceKeyBytes=" + Arrays.toString(resourceKeyBytes) +
            ", dataPathLength=" + dataPathLength +
            ", dataPath=" + Arrays.toString(dataPath) +
            ", startIndex=" + startIndex +
            ", endIndex=" + endIndex +
            ", dataLength=" + dataLength +
            ", crTime=" + crTime +
            '}';
    }
}
