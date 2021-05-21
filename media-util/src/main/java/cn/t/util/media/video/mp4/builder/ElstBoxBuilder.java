package cn.t.util.media.video.mp4.builder;

import cn.t.util.common.digital.ByteSequence;
import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level4.Edit;
import cn.t.util.media.video.mp4.modal.level4.ElstBox;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElstBoxBuilder extends AbstractBoxBuilder {

    @Override
    public List<Box> build(byte[] bs) throws IOException {
        List<Box> boxList = new ArrayList<>();
        ElstBox elstBox = new ElstBox();
        boxList.add(elstBox);
        ByteSequence byteSequence = new ByteSequence(bs);
        //version
        elstBox.setVersion(byteSequence.readByte());
        byte[] flags = new byte[3];
        long l = byteSequence.read(flags);
        if (l < flags.length) {
            throw new EOFException("bytes not enough");
        }
        //flags
        elstBox.setFlags(flags);
        elstBox.setCount(byteSequence.readInt());
        for (int i = 0; i < elstBox.getCount(); i++) {
            Edit edit = new Edit();
            edit.setDuration(byteSequence.readInt());
            edit.setMediaTime(byteSequence.readInt());
            edit.setRate(byteSequence.readInt());
            if (edit.getRate() != 0) {
                edit.setRate(1);
            }
            elstBox.getEditList().add(edit);
        }
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.ELST.value;
    }
}
