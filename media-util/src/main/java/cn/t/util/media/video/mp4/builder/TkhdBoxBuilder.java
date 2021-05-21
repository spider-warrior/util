package cn.t.util.media.video.mp4.builder;

import cn.t.util.common.digital.ByteSequence;
import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level3.TkhdBox;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TkhdBoxBuilder extends AbstractBoxBuilder {

    @Override
    public List<Box> build(byte[] bs) throws IOException {
        List<Box> boxList = new ArrayList<>();
        TkhdBox tkhdBox = new TkhdBox();
        boxList.add(tkhdBox);
        ByteSequence byteSequence = new ByteSequence(bs);
        //version
        tkhdBox.setVersion(byteSequence.readByte());
        byte[] flags = new byte[3];
        long l = byteSequence.read(flags);
        if (l < flags.length) {
            throw new EOFException("bytes not enough");
        }
        //flags
        tkhdBox.setFlag(flags);
        //create time
        tkhdBox.setCreationTime(byteSequence.readInt());
        //modification time
        tkhdBox.setModificationTime(byteSequence.readInt());
        //track id
        tkhdBox.setTrackId(byteSequence.readInt());
        //reserved
        l = byteSequence.skip(4);
        if (l < 4) {
            throw new EOFException("bytes not enough");
        }
        //duration
        tkhdBox.setDuration(byteSequence.readInt());
        //reserved
        l = byteSequence.skip(8);
        if (l < 8) {
            throw new EOFException("bytes not enough");
        }
        //layer
        tkhdBox.setLayer(byteSequence.readShort());
        //alternate group
        tkhdBox.setAlternateGroup(byteSequence.readShort());
        //volume
        tkhdBox.setVolume(byteSequence.readShort());
        //reserved
        l = byteSequence.skip(2);
        if (l < 2) {
            throw new EOFException("bytes not enough");
        }
        byte[] matrix = new byte[36];
        l = byteSequence.read(matrix);
        if (l < matrix.length) {
            throw new EOFException("bytes not enough");
        }
        //matrix structure
        tkhdBox.setMatrix(matrix);
        //width
        tkhdBox.setWidth(byteSequence.readInt());
        //height
        tkhdBox.setHeight(byteSequence.readInt());
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.TKHD.value;
    }
}
