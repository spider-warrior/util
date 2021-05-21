package cn.t.util.io.crypto.entity;

import java.util.Arrays;

public class IndexSegment {

    /**
     * 文件类型
     * */
    private byte fileType;

    /**
     * 文件名字节长度
     * */
    private byte fileNameByteLength;

    /**
     * 文件名
     * */
    private byte[] fileNameBytes = new byte[0];

    /**
     * 数据长度
     * */
    private long dataLength;

    public byte getFileType() {
        return fileType;
    }

    public void setFileType(byte fileType) {
        this.fileType = fileType;
    }

    public byte getFileNameByteLength() {
        return fileNameByteLength;
    }

    public void setFileNameByteLength(byte fileNameByteLength) {
        this.fileNameByteLength = fileNameByteLength;
    }

    public byte[] getFileNameBytes() {
        return fileNameBytes;
    }

    public void setFileNameBytes(byte[] fileNameBytes) {
        this.fileNameBytes = fileNameBytes;
    }

    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String toString() {
        return "IndexSegment{" +
            "fileType=" + fileType +
            ", fileNameByteLength=" + fileNameByteLength +
            ", fileNameBytes=" + Arrays.toString(fileNameBytes) +
            ", dataLength=" + dataLength +
            '}';
    }
}
