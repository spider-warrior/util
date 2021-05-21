package cn.t.util.media.video.mp4.modal.level3;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.ContainerBox;

/********************************************************************************************
 **        字段名称            |    长度(bytes)   |        有关描述
 --------------------------------------------------------------------------------------------
 **        boxsize            |    4            |        box的长度
 **        boxtype            |    4            |        box的类型
 ********************************************************************************************/
public class EdtsBox extends ContainerBox {
    public EdtsBox() {
        type = BoxType.EDTS.value;
    }
}
