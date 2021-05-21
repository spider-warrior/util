package cn.t.util.io.crypto.entity;

import java.util.Arrays;

public class ResourceData {

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
     * 文件内容字节长度
     * */
    private int dataByteLength;

    /**
     * 数据
     * */
    private byte[] data;

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

    public int getDataByteLength() {
        return dataByteLength;
    }

    public void setDataByteLength(int dataByteLength) {
        this.dataByteLength = dataByteLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResourceData{" +
            "fileType=" + fileType +
            ", fileNameByteLength=" + fileNameByteLength +
            ", fileNameBytes=" + Arrays.toString(fileNameBytes) +
            ", dataByteLength=" + dataByteLength +
            ", data=" + Arrays.toString(data) +
            '}';
    }
}
