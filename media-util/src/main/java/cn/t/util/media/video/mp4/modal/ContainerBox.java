package cn.t.util.media.video.mp4.modal;

import java.util.ArrayList;
import java.util.List;

public class ContainerBox extends Box {

    protected List<Box> boxList = new ArrayList<>();

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}
