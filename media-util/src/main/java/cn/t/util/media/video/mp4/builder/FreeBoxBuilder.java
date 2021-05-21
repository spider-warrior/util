package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level1.FreeBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FreeBoxBuilder extends AbstractBoxBuilder {

    @Override
    public List<Box> build(byte[] bs) throws IOException {
        List<Box> boxList = new ArrayList<>();
        FreeBox freeBox = new FreeBox();
        boxList.add(freeBox);
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.FREE.value;
    }
}
