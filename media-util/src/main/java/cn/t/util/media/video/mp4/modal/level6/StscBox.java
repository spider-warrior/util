package cn.t.util.media.video.mp4.modal.level6;

import cn.t.util.media.video.mp4.modal.Box;

/**
 * Sample to chunk 的映射表
 * **********************************************************************************************************
 * BoxSize          :40
 * BoxType          :stsc
 * Version          :0
 * Flag             :0
 * EntryCount       :2
 * FirstChunk       :1,  SampPerChunk       :1, SampDesIndex       :1
 * FirstChunk       :83, SampPerChunk       :5, SampDesIndex       :1
 * <p>
 * 说明一共87帧数据，放在83个chunk中。1~82个chunk每个里面放1帧，第83个chunk放了5帧。
 * **********************************************************************************************************
 */
public class StscBox extends Box {

    private byte version;

    private byte[] flag;

    private int sampleSize;

    private int sampleCount;

    private int vecSampleSize;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte[] getFlag() {
        return flag;
    }

    public void setFlag(byte[] flag) {
        this.flag = flag;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public int getVecSampleSize() {
        return vecSampleSize;
    }

    public void setVecSampleSize(int vecSampleSize) {
        this.vecSampleSize = vecSampleSize;
    }
}
