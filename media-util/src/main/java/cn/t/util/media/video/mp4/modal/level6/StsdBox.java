package cn.t.util.media.video.mp4.modal.level6;

import cn.t.util.media.video.mp4.modal.Box;


/**
 * Sample Description Box，解析stsd可获得coding类型、视频宽高、音频samplesize、channelcount这些和解码器有关信息。
 * **********************************************************************************************************
 * *										stsd
 * *
 * -------------------------------------------------------------------------------------------------------------
 * *		字段名称			|	长度(bytes)	|		有关描述
 * -------------------------------------------------------------------------------------------------------------
 * *		boxsize				|	4			|		box的长度
 * *		boxtype				|	4			|		box的类型
 * *		version				|	1			|		box版本0或1 一般为0 (以下字节数均按version=0)
 * *		flags				|	3			|
 * *		entry_count			|	4			|
 * ***********************************************************************************************************
 * <p>
 * BoxSize         :198
 * BoxType         :stsd
 * Version         :0
 * Flags           :1
 * EntryCount      :1
 ***********************************************************************************************************/
public class StsdBox extends Box {

    private byte version;

    private byte[] flag;

    private int entryCount;

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

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

}
