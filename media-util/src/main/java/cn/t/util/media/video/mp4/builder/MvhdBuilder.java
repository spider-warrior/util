package cn.t.util.media.video.mp4.builder;

import cn.t.util.common.digital.ByteSequence;
import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level2.MvhdBox;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MvhdBuilder extends AbstractBoxBuilder {

    @Override
    public List<Box> build(byte[] content) throws IOException {
        List<Box> boxList = new ArrayList<>();
        MvhdBox mvhdBox = new MvhdBox();
        boxList.add(mvhdBox);
        ByteSequence byteSequence = new ByteSequence(content);
        //version
        mvhdBox.setVersion(byteSequence.readByte());
        byte[] flags = new byte[3];
        long l = byteSequence.read(flags);
        if (l < flags.length) {
            throw new EOFException("bytes not enough");
        }
        //flags
        mvhdBox.setFlag(flags);
        //creation time
        mvhdBox.setCreationTime(byteSequence.readInt());
        //modification time
        mvhdBox.setModificationTIme(byteSequence.readInt());
        //time scale
        mvhdBox.setTimeScale(byteSequence.readInt());
        //duration
        mvhdBox.setDuration(byteSequence.readInt());
        //rate
        mvhdBox.setRate(byteSequence.readInt());
        //volume
        mvhdBox.setVolume(byteSequence.readShort());
        //reserved
        l = byteSequence.skip(10);
        if (l < 10) {
            throw new EOFException("bytes not enough");
        }
        byte[] matrix = new byte[36];
        l = byteSequence.read(matrix);
        if (l < matrix.length) {
            throw new EOFException("bytes not enough");
        }
        mvhdBox.setMatrix(matrix);
        //pre-defined
        l = byteSequence.skip(24);
        if (l < 24) {
            throw new EOFException("bytes not enough");
        }
        //next track id
        mvhdBox.setNextTrackId(byteSequence.readInt());
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.MVHD.value;
    }
}
