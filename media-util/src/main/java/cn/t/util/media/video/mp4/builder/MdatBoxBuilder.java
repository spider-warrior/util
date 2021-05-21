package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level1.MdatBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MdatBoxBuilder extends AbstractBoxBuilder {

    @Override
    public List<Box> build(byte[] bs) throws IOException {
        List<Box> boxList = new ArrayList<>();
        MdatBox mdatBox = new MdatBox();
        boxList.add(mdatBox);
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.MDAT.value;
    }

}
