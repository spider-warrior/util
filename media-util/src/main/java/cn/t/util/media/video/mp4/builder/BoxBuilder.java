package cn.t.util.media.video.mp4.builder;

import cn.t.util.media.video.mp4.modal.Box;

import java.io.IOException;
import java.util.List;

public interface BoxBuilder {

    boolean support(String type);

    List<Box> build(byte[] content) throws IOException;

    List<BoxBuilder> getBoxBuilderList();

}
