package cn.t.util.media.video.mp4;

import cn.t.util.media.video.mp4.builder.*;

public final class Mp4BoxBuilder extends AbstractBoxBuilder {


    {
        addBoxBuilder(new FtypBoxBuilder());
        addBoxBuilder(new MoovBoxBuilder());
        addBoxBuilder(new FreeBoxBuilder());
        addBoxBuilder(new MdatBoxBuilder());
    }

    @Override
    public String getSupport() {
        return "";
    }
}
