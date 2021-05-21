package cn.t.util.media.video.mp4.modal.level6;

import cn.t.util.media.video.mp4.modal.Box;

/**
 * Sample Size Boxes，每个Sample大小的表
 * **********************************************************************************************************
 * <p>
 * BoxSize          :368
 * BoxType          :stsz
 * Version          :0
 * Flag             :0
 * SampleSize       :0
 * SampleCount      :87
 * SampleSize       :15168
 * SampleSize       :8281
 * SampleSize       :6639
 * SampleSize       :7716
 * SampleSize       :7286
 * <p>
 * **********************************************************************************************************
 */
public class StszBox extends Box {


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
