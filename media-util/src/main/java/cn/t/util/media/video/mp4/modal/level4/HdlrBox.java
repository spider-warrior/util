package cn.t.util.media.video.mp4.modal.level4;

import cn.t.util.media.video.mp4.modal.Box;

/************************************************************************************************************
 **                                        hdlr
 **
 -------------------------------------------------------------------------------------------------------------
 **        字段名称            　　 |    长度(bytes)    |        有关描述
 -------------------------------------------------------------------------------------------------------------
 **        boxsize                |    4             |        box的长度
 **        boxtype                |    4             |        box的类型
 **        version                |    1             |        box版本0或1 一般为0 (以下字节数均按version=0)
 **        flags                  |    3             |
 **        pre-defined            |    4             |
 **        handler type           |    4             |        在media box中，该值为4个字符
 　　　　　　"vide"— video track
 　　　　　　"soun"— audio track
 　　　　　　"hint"— hint track
 **        reserved               |    12            |
 **        name                   |    不定           |        track type name，以‘\0’结尾的字符串
 *
 * ************************************************************************************************************
 *
 *
 * BoxSize         :45
 * BoxType         :hdlr
 * Version         :0
 * Flags           :0
 * Predefined      :0
 * HandlerType     :vide
 * Reserved        :
 * Name            :VideoHandler
 ************************************************************************************************************/
public class HdlrBox extends Box {

    private byte version;

    private byte[] flag;

    private int preDefined;

    private int handlerType;

    private byte[] reserved;

    private String name;

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

    public int getPreDefined() {
        return preDefined;
    }

    public void setPreDefined(int preDefined) {
        this.preDefined = preDefined;
    }

    public int getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(int handlerType) {
        this.handlerType = handlerType;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
