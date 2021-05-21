package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.enums.BoxType;

public class TrakBoxBuilder extends AbstractBoxBuilder {

    {
        addBoxBuilder(new TkhdBoxBuilder());
        addBoxBuilder(new EdtsBoxBuilder());
    }

    @Override
    public String getSupport() {
        return BoxType.TRAK.value;
    }

}
