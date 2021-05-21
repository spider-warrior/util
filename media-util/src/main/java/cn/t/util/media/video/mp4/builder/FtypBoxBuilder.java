package cn.t.util.media.video.mp4.builder;

import cn.t.util.common.digital.ByteSequence;
import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;
import cn.t.util.media.video.mp4.modal.level1.FtypBox;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FtypBoxBuilder extends AbstractBoxBuilder {

    /**
     * 读取FtypBox
     */
    @Override
    public List<Box> build(byte[] content) throws IOException {
        byte[] fourBytes = new byte[4];
        ByteSequence byteSequence = new ByteSequence(content);
        int start = byteSequence.getIndex();
        List<Box> boxList = new ArrayList<>();
        FtypBox ftypBox = new FtypBox();
        boxList.add(ftypBox);
        ftypBox.setSize(content.length);
        ftypBox.setType(BoxType.FTYP.value);
        //brand
        int readLength = byteSequence.read(fourBytes);
        if (readLength < 4) {
            throw new EOFException("bytes not enough");
        }
        ftypBox.setMajorBrand(new String(fourBytes));
        //version
        ftypBox.setMinorVersion(byteSequence.readInt());
        byte[] compatibleBrandsBytes = new byte[content.length - (byteSequence.getIndex() - start)];
        //compatibility
        readLength = byteSequence.read(compatibleBrandsBytes);
        if (readLength < compatibleBrandsBytes.length) {
            throw new EOFException("bytes not enough");
        }
        ftypBox.setCompatibleBrands(new String(compatibleBrandsBytes));
        return boxList;
    }

    @Override
    public String getSupport() {
        return BoxType.FTYP.value;
    }
}
