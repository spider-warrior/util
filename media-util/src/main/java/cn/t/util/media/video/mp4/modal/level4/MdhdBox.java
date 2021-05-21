package cn.t.util.media.video.mp4.modal.level4;

import cn.t.util.media.video.mp4.modal.Box;

/************************************************************************************************************
 **                                        tkhd
 **
 -------------------------------------------------------------------------------------------------------------
 **        字段名称            　　|　　    长度(bytes)　　　|        有关描述
 -------------------------------------------------------------------------------------------------------------
 **        boxsize               |    4            　　  |        box的长度
 **        boxtype               |    4            　　  |        box的类型
 **        version               |    1　　　　　　　　　  |        box版本0或1 一般为0 (以下字节数均按version=0)
 **        flags                 |    3            　　  |
 **        creation_time         |    4            　　  |        创建时间（相对于UTC时间1904 - 01 - 01零点的秒数）
 **        modification_time     |    4            　　  |        修改时间
 **        time_scale            |    4            　　  |
 **        duration              |    4            　　　|        track的时间长度
 **        language              |    2            　　　|        媒体语言码,最高位为0 后面15位为3个字符[见ISO 639-2/T标准中定义]
 **        pre-defined           |    2            　　  |        保留位
 * ************************************************************************************************************
 *
 * BoxSize         :32
 * BoxType         :mdhd
 * Version         :0
 * Flags           :0
 * CreateTime      :0
 * ModifyTime      :0
 * TimeScale       :12288
 * Duration        :44544
 * Language        :21956
 * Predefined      :0
 *
 ************************************************************************************************************/
public class MdhdBox extends Box {

    private byte version;

    private byte[] flag;

    private int creationTime;

    private int modificationTime;

    private int timeScale;

    private int duration;

    private short language;

    private short preDefined;

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

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public int getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(int modificationTime) {
        this.modificationTime = modificationTime;
    }

    public int getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(int timeScale) {
        this.timeScale = timeScale;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getLanguage() {
        return language;
    }

    public void setLanguage(short language) {
        this.language = language;
    }

    public short getPreDefined() {
        return preDefined;
    }

    public void setPreDefined(short preDefined) {
        this.preDefined = preDefined;
    }

}
