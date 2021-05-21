package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level3.EdtsBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdtsBoxBuilder extends AbstractBoxBuilder {

    {
        addBoxBuilder(new ElstBoxBuilder());
    }

    @Override
    public List<Box> build(byte[] bs) throws IOException {
        List<Box> boxList = new ArrayList<>();
        EdtsBox edtsBox = new EdtsBox();
        boxList.add(edtsBox);
        boxList.addAll(super.build(bs));
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.EDTS.value;
    }
}
