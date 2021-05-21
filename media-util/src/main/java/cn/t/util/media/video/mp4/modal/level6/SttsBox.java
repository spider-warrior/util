package cn.t.util.media.video.mp4.modal.level6;

import cn.t.util.media.video.mp4.modal.Box;

/**
 * Decoding Time to Sample Box，时间戳和Sample映射表
 * **********************************************************************************************************
 * <p>
 * BoxSize          :24
 * BoxType          :stts
 * EntryCount       :1
 * SampleCount      :87
 * SampleDelta      :512
 * <p>
 * 以上说明该视频包含87帧数据，每帧包含512个采样。总共512*87=44544个采样。
 * Duration / TimeScale = 44544 / 12288 = 3.625秒 正是我们的视频播放长度。
 * 12288 / 512 = 24 p/s (帧率)。
 * **********************************************************************************************************
 */
public class SttsBox extends Box {

    private long entryCount;

    private int sampleCount;

    private int sampleDelta;

    public long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(long entryCount) {
        this.entryCount = entryCount;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public int getSampleDelta() {
        return sampleDelta;
    }

    public void setSampleDelta(int sampleDelta) {
        this.sampleDelta = sampleDelta;
    }

    @Override
    public String toString() {
        return "SttsBox{" +
            "entryCount=" + entryCount +
            ", sampleCount=" + sampleCount +
            ", sampleDelta=" + sampleDelta +
            "} " + super.toString();
    }
}
