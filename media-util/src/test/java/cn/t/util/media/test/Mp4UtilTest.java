package cn.t.util.media.test;

import cn.t.util.common.LoggerUtil;
import cn.t.util.io.FileUtil;
import cn.t.util.media.video.mp4.Mp4BoxBuilder;
import cn.t.util.media.video.mp4.modal.Box;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;

public class Mp4UtilTest {

    private static final Logger logger = LoggerFactory.getLogger(Mp4UtilTest.class);

    private Mp4BoxBuilder mp4BoxBuilder;
    private DataInputStream dis;

    @Before
    public void init() throws IOException {
        LoggerUtil.setSlf4jRootLoggerLevel(Level.INFO);
        String file = "/home/amen/Desktop/sample_h264_1mbit.mp4";
        dis = FileUtil.getResourceDataInputStream(file);
        mp4BoxBuilder = new Mp4BoxBuilder();
    }

    @Test
    public void readM4pBox() throws Exception {
        int size;
        byte[] type = new byte[4];
        while ((size = dis.readInt()) > 0) {
            logger.info("box size: {}", size);
            int typeLength = dis.read(type);
            if (typeLength > 0) {
                logger.info("box type: {}", new String(type));
                //如果使用large size
                if (size == 1) {
                    long largeSize = dis.readLong();
                    logger.info("box large size: {}", largeSize);
                }
                //如果为用户自定义类型
                if ("uuid".equals(new String(type))) {
                    byte[] userTypeBytes = new byte[16];
                    int userTypeLength = dis.read(userTypeBytes);
                    if (userTypeLength > 0) {
                        logger.info("box user type: {}", new String(userTypeBytes));
                    }
                }
            }
            //内容
            byte[] bs = new byte[size - 8];
            int contentLength = dis.read(bs);
            logger.info("content: {}", new String(bs));
            if (contentLength < 0) {
                break;
            }
        }
        System.out.println("end....");
    }

    @Test
    public void testParseMp4() throws IOException {
        byte[] fileBytes = new byte[dis.available()];
        int l = dis.read(fileBytes);
        if (l < fileBytes.length) {
            throw new EOFException("bytes not enough");
        }
        List<Box> boxList = mp4BoxBuilder.build(fileBytes);
        System.out.println(boxList);
    }

    @After
    public void destroy() throws IOException {
        dis.close();
    }
}
