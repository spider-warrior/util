package cn.t.util.media.video.mp4.modal.level6;

import cn.t.util.media.video.mp4.modal.Box;

/**
 * 关键帧索引表
 * **********************************************************************************************************
 * BoxSize             :48
 * BoxType             :stss
 * EntryCount          :8
 * SampleNum           :1
 * SampleNum           :13
 * SampleNum           :25
 * SampleNum           :37
 * SampleNum           :49
 * SampleNum           :61
 * <p>
 * 第1、13、25、37、49、61、73、85.... 这些帧都是关键帧。
 * 每12帧就有一个关键帧， 前面我们计算得知fps是24。由此可知该视频关键帧间隔为0.5秒。
 * **********************************************************************************************************
 */
public class StssBox extends Box {

    private long entryCount;

    private int sampleNum;

    public long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(long entryCount) {
        this.entryCount = entryCount;
    }

    public int getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(int sampleNum) {
        this.sampleNum = sampleNum;
    }
}
