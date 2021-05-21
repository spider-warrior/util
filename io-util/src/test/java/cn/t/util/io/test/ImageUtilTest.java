package cn.t.util.io.test;

import cn.t.util.io.ImageUtil;
import org.junit.Test;

public class ImageUtilTest {

    @Test
    public void validateImageTest() {
        String path = "/home/amen/Desktop/2.png";
        System.out.println("result: " + ImageUtil.validateFile(path));
    }
}
