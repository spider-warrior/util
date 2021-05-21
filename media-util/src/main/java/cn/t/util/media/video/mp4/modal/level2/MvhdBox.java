package cn.t.util.media.video.mp4.modal.level2;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;


/************************************************************************************************************
 **											mvhd
 **
 --------------------------------------------------------------------------------------------
 **		字段名称			|	长度(bytes)	|		有关描述
 --------------------------------------------------------------------------------------------
 **		boxsize				|	4			|		box的长度
 **		boxtype				|	4			|		box的类型
 **		version				|	1			|		box版本，0或1，一般为0（以下字节数均按version = 0）
 **		flags				|	3			|
 **		creation time		|	4			|		创建时间（相对于UTC时间1904 - 01 - 01零点的秒数）
 **		modification time	|	4			|		修改时间
 **		time scale			|	4			|		文件媒体在1秒时间内的刻度值，可以理解为1秒长度的时间单元数
 **		duration			|	4			|		该track的时间长度，用duration和time scale值可以计算track时长
 **		rate				|	4			|		推荐播放速率，高16位和低16位分别为小数点整数部分和小数部分，即[16.16] 格式.该值为1.0（0x00010000）表示正常前向播放
 **		volume				|	2			|		与rate类似，[8.8] 格式，1.0（0x0100）表示最大音量
 **		reserved			|	10			|		保留位
 **		matrix				|	36			|		视频变换矩阵
 **		pre-defined			|	24			|
 **		next track id		|	4			|		下一个track使用的id号
 * ************************************************************************************************************
 *
 * BoxSize         :108
 * BoxType         :mvhd
 * Version         :0
 * Flags           :0
 * CreateTime      :0
 * ModifyTime      :0
 * TimeScale       :1000
 * Duration        :3625
 * Rate            :1.000000
 * Volume          :1.000000
 * Reserved        :
 * Matrix          :
 * Predefined      :
 * NextTrackId     :3
 *
 ************************************************************************************************************/
public class MvhdBox extends Box {

    private byte version;

    private byte[] flag;

    private int creationTime;

    private int modificationTIme;

    private int timeScale;

    private int duration;

    private int rate;

    private short volume;

    private byte[] reserved;

    private byte[] matrix;

    private byte[] preDefined;

    private int nextTrackId;

    {
        type = BoxType.MVHD.value;
    }

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

    public int getModificationTIme() {
        return modificationTIme;
    }

    public void setModificationTIme(int modificationTIme) {
        this.modificationTIme = modificationTIme;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public short getVolume() {
        return volume;
    }

    public void setVolume(short volume) {
        this.volume = volume;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }

    public byte[] getMatrix() {
        return matrix;
    }

    public void setMatrix(byte[] matrix) {
        this.matrix = matrix;
    }

    public byte[] getPreDefined() {
        return preDefined;
    }

    public void setPreDefined(byte[] preDefined) {
        this.preDefined = preDefined;
    }

    public int getNextTrackId() {
        return nextTrackId;
    }

    public void setNextTrackId(int nextTrackId) {
        this.nextTrackId = nextTrackId;
    }

}
