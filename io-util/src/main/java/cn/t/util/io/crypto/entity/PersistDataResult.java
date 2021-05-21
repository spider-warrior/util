package cn.t.util.io.crypto.entity;

import java.util.Arrays;

public class PersistDataResult {

    private byte[] dataPath = new byte[0];
    private long dataLength;
    private long startIndex = -1;
    private long endIndex = -1;

    public byte[] getDataPath() {
        return dataPath;
    }

    public void setDataPath(byte[] dataPath) {
        this.dataPath = dataPath;
    }

    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
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

    @Override
    public String toString() {
        return "PersistDataResult{" +
            "dataPath=" + Arrays.toString(dataPath) +
            ", dataLength=" + dataLength +
            ", startIndex=" + startIndex +
            ", endIndex=" + endIndex +
            '}';
    }
}
