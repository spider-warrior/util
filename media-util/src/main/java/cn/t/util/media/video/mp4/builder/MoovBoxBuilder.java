package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level1.MoovBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoovBoxBuilder extends AbstractBoxBuilder {

    {
        addBoxBuilder(new MvhdBuilder());
        addBoxBuilder(new TrakBoxBuilder());
    }

    @Override
    public List<Box> build(byte[] content) throws IOException {
        List<Box> boxList = new ArrayList<>();
        MoovBox moovBox = new MoovBox();
        boxList.add(moovBox);
        moovBox.getBoxList().addAll(super.build(content));
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.MOOV.value;
    }
}
