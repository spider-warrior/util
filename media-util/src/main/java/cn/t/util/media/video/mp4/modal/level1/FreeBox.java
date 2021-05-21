package cn.t.util.media.video.mp4.modal.level1;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;

/********************************************************************************************
 **        字段名称            |    长度(bytes)   |        有关描述
 --------------------------------------------------------------------------------------------
 **        boxsize            |    4            |        box的长度
 **        boxtype            |    4            |        box的类型
 ********************************************************************************************/
public class FreeBox extends Box {

    public FreeBox() {
        type = BoxType.FREE.value;
    }
}
