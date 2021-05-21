package cn.t.util.media.video.mp4.modal.level5;

import cn.t.util.media.video.mp4.modal.Box;

/**
 * headerbox会根据前面的handler type数值选择哪个box。
 * ---> "vide"—vmhd 视频
 * ---> "soun"— smhd 音频
 * ---> "hint"—hmhd 忽略
 */

/************************************************************************************************************
 **                                        vmhd
 **
 -------------------------------------------------------------------------------------------------------------
 **        字段名称            |    长度(bytes)    |        有关描述
 -------------------------------------------------------------------------------------------------------------
 **        boxsize                |    4            |        box的长度
 **        boxtype                |    4            |        box的类型
 **        version                |    1            |        box版本0或1 一般为0 (以下字节数均按version=0)
 **        flags                |    3            |
 **        graphics_mode        |    4            |        视频合成模式，为0时拷贝原始图像，否则与opcolor进行合成
 **        opcolor                |    2 ×3        |        ｛red，green，blue｝
 * ************************************************************************************************************
 *
 * BoxSize         :20
 * BoxType         :vmhd
 * Version         :0
 * Flags           :1
 * GraphicsMode    :0.000000
 * Opcolor         :00 00 00 00 00
 ************************************************************************************************************/
public class HeaderBox extends Box {

    private byte version;

    private byte[] flag;

    private int graphicsMode;

    private short[] opcolor;

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

    public int getGraphicsMode() {
        return graphicsMode;
    }

    public void setGraphicsMode(int graphicsMode) {
        this.graphicsMode = graphicsMode;
    }

    public short[] getOpcolor() {
        return opcolor;
    }

    public void setOpcolor(short[] opcolor) {
        this.opcolor = opcolor;
    }

}
