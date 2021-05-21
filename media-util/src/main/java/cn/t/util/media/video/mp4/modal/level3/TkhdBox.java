package cn.t.util.media.video.mp4.modal.level3;

import cn.t.util.media.video.mp4.modal.Box;

/************************************************************************************************************
 **                                        tkhd
 **
 -------------------------------------------------------------------------------------------------------------
 **        字段名称            　　 |    长度(bytes)   |        有关描述
 -------------------------------------------------------------------------------------------------------------
 **        boxsize                |    4            |        box的长度
 **        boxtype                |    4            |        box的类型
 **        version                |    1            |        box版本，0或1，一般为0。（以下字节数均按version = 0）
 **        flags                  |    3            |        按位或操作结果值，预定义如下;
 　　　　 0x000001 track_enabled，否则该track不被播放；
 　　　　 0x000002 track_in_movie，表示该track在播放中被引用；
 　　　　 0x000004 track_in_preview，表示该track在预览时被引用。
 　　　　 一般该值为7，如果一个媒体所有track均未设置track_in_movie和track_in_preview,将被理解为所有track均设置了这两项;
 　　　　 对于hint track，该值为0;
 **        creation_time          |    4            |        创建时间（相对于UTC时间1904 - 01 - 01零点的秒数）
 **        modification_time      |    4            |        修改时间
 **        track_id               |    4            |        id号 不能重复且不能为0
 **        reserved               |    4            |        保留位
 **        duration               |    4            |        track的时间长度
 **        reserved               |    8            |        保留位
 **        layer                  |    2            |        视频层，默认为0，值小的在上层
 **        alternate_group        |    2            |        track分组信息，默认为0表示该track未与其他track有群组关系
 **        volume                 |    2            |        [8.8] 格式，如果为音频track，1.0（0x0100）表示最大音量；否则为0
 **        reserved               |    2            |        保留位
 **        matrix                 |    36           |        视频变换矩阵
 **        width                  |    4            |        宽
 **        height                 |    4            |        高，均为[16.16] 格式值 与sample描述中的实际画面大小比值，用于播放时的展示宽高
 * ************************************************************************************************************
 *
 *
 * BoxSize         :92
 * BoxType         :tkhd
 * Version         :0
 * Flags           :3
 * CreateTime      :0
 * ModifyTime      :0
 * TrackId         :1
 * Reserved1       :
 * Duration        :3625
 * Reserved2       :
 * Layer           :0
 * AlterGroup      :0
 * Volume          :0.000000
 * Reserved3       :
 * Matrix          :
 * Width           :480.000000
 * Height          :480.000000
 ************************************************************************************************************/
public class TkhdBox extends Box {

    private byte version;

    private byte[] flag;

    private int creationTime;

    private int modificationTime;

    private int trackId;

    private byte[] reserved1;

    private int duration;

    private byte[] reserved2;

    private short layer;

    private short alternateGroup;

    private short volume;

    private byte[] reserved3;

    private byte[] matrix;

    private int width;

    private int height;

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

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public byte[] getReserved1() {
        return reserved1;
    }

    public void setReserved1(byte[] reserved1) {
        this.reserved1 = reserved1;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public byte[] getReserved2() {
        return reserved2;
    }

    public void setReserved2(byte[] reserved2) {
        this.reserved2 = reserved2;
    }

    public short getLayer() {
        return layer;
    }

    public void setLayer(short layer) {
        this.layer = layer;
    }

    public short getAlternateGroup() {
        return alternateGroup;
    }

    public void setAlternateGroup(short alternateGroup) {
        this.alternateGroup = alternateGroup;
    }

    public short getVolume() {
        return volume;
    }

    public void setVolume(short volume) {
        this.volume = volume;
    }

    public byte[] getReserved3() {
        return reserved3;
    }

    public void setReserved3(byte[] reserved3) {
        this.reserved3 = reserved3;
    }

    public byte[] getMatrix() {
        return matrix;
    }

    public void setMatrix(byte[] matrix) {
        this.matrix = matrix;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
