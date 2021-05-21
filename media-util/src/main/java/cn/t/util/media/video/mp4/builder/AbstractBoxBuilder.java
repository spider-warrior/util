package cn.t.util.media.video.mp4.builder;

import cn.t.util.common.digital.ByteSequence;
import cn.t.util.media.video.mp4.modal.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBoxBuilder implements BoxBuilder {

    private static final Logger logger = LoggerFactory.getLogger(AbstractBoxBuilder.class);
    protected List<BoxBuilder> boxBuilderList = new ArrayList<>();
    private String supportType;

    {
        initSupport();
    }

    public List<Box> build(byte[] bs) throws IOException {
        ByteSequence byteSequence = new ByteSequence(bs);
        byte[] fourBytes = new byte[4];
        long readLength;
        List<Box> boxList = new ArrayList<>();
        out:
        while (byteSequence.available() > 0) {
            int start = byteSequence.getIndex();
            //size
            long size = byteSequence.readInt();
            if (size < 0) {
                throw new EOFException("bytes not enough");
            }
            //type
            readLength = byteSequence.read(fourBytes);
            String type = new String(fourBytes);
            if (readLength < 0) {
                throw new EOFException("bytes not enough");
            }
            if (size == 1) {
                //large size
                size = byteSequence.readLong();
            }
            if ("uuid".equals(type)) {
                byte[] userTypeBytes = new byte[16];
                //user type
                readLength = byteSequence.read(userTypeBytes);
                if (readLength < 16) {
                    throw new EOFException("bytes not enough");
                }
                type = new String(userTypeBytes);
            }
            //挑选Builder
            for (BoxBuilder builder : boxBuilderList) {
                if (builder.support(type)) {
                    byte[] content = new byte[(int) size - (byteSequence.getIndex() - start)];
                    readLength = byteSequence.read(content);
                    if (readLength < content.length) {
                        throw new EOFException("bytes not enough");
                    }
                    boxList.addAll(builder.build(content));
                    continue out;
                }
            }
            logger.warn("no BoxBuilder found by type: {}", new String(fourBytes));
            readLength = byteSequence.skip(size - 8);
            if (readLength < 0) {
                throw new EOFException("bytes not enough");
            }
        }
        return boxList;
    }

    @Override
    public boolean support(String type) {
        return supportType.equals(type);
    }

    @Override
    public List<BoxBuilder> getBoxBuilderList() {
        return boxBuilderList;
    }

    protected void addBoxBuilder(BoxBuilder boxBuilder) {
        if (boxBuilder != null) {
            boxBuilderList.add(boxBuilder);
        }
    }

    protected void addBoxBuilderList(List<BoxBuilder> boxBuilderList) {
        if (boxBuilderList != null && boxBuilderList.size() > 0) {
            this.boxBuilderList.addAll(boxBuilderList);
        }
    }

    public void initSupport() {
        supportType = getSupport();
    }

    public abstract String getSupport();


}
