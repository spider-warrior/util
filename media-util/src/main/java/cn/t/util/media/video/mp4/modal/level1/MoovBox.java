package cn.t.util.media.video.mp4.modal.level1;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;

import java.util.ArrayList;
import java.util.List;

/********************************************************************************************
 **        字段名称            |    长度(bytes)   |        有关描述
 --------------------------------------------------------------------------------------------
 **        boxsize            |    4            |        box的长度
 **        boxtype            |    4            |        box的类型
 ********************************************************************************************/
public class MoovBox extends Box {

    private List<Box> boxList = new ArrayList<>();

    public MoovBox() {
        type = BoxType.MOOV.value;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}
