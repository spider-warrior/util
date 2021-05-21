package cn.t.util.media.test;

import cn.t.util.media.code.TwoDimensionCodeUtil;
import cn.t.util.media.code.zxing.ZxingTwoDimensionCodeConfig;
import com.google.zxing.Result;
import org.junit.Assert;
import org.junit.Test;

public class TwoDimensionCodeTest {

    public static void main(String[] args) {
        String goodsName = "积分";
        String[] wish = goodsName.split("。");
        System.out.println(wish);
    }

    /**
     * 测试二维码写出到指定目录
     */
    @Test
    public void testZxingWriteCodeFile() {
        String content = "http://www.baidu.com?a=1";
        String path = "/home/amen/Desktop/code.png";
        ZxingTwoDimensionCodeConfig codeConfig = new ZxingTwoDimensionCodeConfig();
        codeConfig.setMargin(10);
        TwoDimensionCodeUtil.zxingWriteCode(codeConfig, content, path);
    }

    /**
     * 测试二维码读取到指定目录
     */
    @Test
    public void testZxingReadCodeFile() {
        String path = "/home/amen/Desktop/qr_code.png";
        ZxingTwoDimensionCodeConfig codeConfig = new ZxingTwoDimensionCodeConfig();
        codeConfig.setMargin(10);
        Result result = TwoDimensionCodeUtil.zxingReadCode(codeConfig, path);
        Assert.assertNotNull(result);
    }

}
