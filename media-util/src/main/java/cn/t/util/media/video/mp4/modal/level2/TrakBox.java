package cn.t.util.media.video.mp4.modal.level2;

import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level3.TkhdBox;

/********************************************************************************************
 **        字段名称            |    长度(bytes)   |        有关描述
 --------------------------------------------------------------------------------------------
 **        boxsize            |    4            |        box的长度
 **        boxtype            |    4            |        box的类型
 ********************************************************************************************/
public class TrakBox extends Box {

    private TkhdBox tkhdBox;

    public TkhdBox getTkhdBox() {
        return tkhdBox;
    }

    public void setTkhdBox(TkhdBox tkhdBox) {
        this.tkhdBox = tkhdBox;
    }
}
