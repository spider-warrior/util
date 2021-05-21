package cn.t.util.media.code;

import cn.t.util.media.code.zxing.BufferedImageLuminanceSource;
import cn.t.util.media.code.zxing.MatrixToImageWriter;
import cn.t.util.media.code.zxing.ZxingTwoDimensionCodeConfig;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class TwoDimensionCodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(TwoDimensionCodeUtil.class);

    /**
     * zxing输出二维码
     * @param codeConfig xxx
     * @param content xxx
     * @param path xxx
     */
    public static void zxingWriteCode(ZxingTwoDimensionCodeConfig codeConfig, String content, String path) {
        //定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, codeConfig.getCharset());
        //就错级别(中等)
        hints.put(EncodeHintType.ERROR_CORRECTION, codeConfig.getErrorCorrectionLevel());
        //边距(默认是5)
        hints.put(EncodeHintType.MARGIN, codeConfig.getMargin());
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, codeConfig.getWidth(), codeConfig.getHeight(), hints);
            Path file = new File(path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, codeConfig.getFormat(), file);
        } catch (Exception e) {
            logger.error("二维码输出异常", e);
        }
    }

    /**
     * zxing读取二维码
     * @param codeConfig xxx
     * @param path xxx
     * @return xxx
     */
    public static Result zxingReadCode(ZxingTwoDimensionCodeConfig codeConfig, String path) {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            //定义二维码的参数
            HashMap<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, codeConfig.getCharset());
            Result result = formatReader.decode(binaryBitmap, hints);
            logger.debug("二维码格式类型: {}", result.getBarcodeFormat());
            logger.debug("二维码文本内容: {}", result.getText());
            return result;
        } catch (IOException | NotFoundException e) {
            logger.error(e.toString());
        }
        return null;
    }

}


