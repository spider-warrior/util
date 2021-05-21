package cn.t.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static boolean validateFile(String path) {
        if (path == null) {
            return false;
        }
        boolean valid = false;
        try {
            Image image = ImageIO.read(new File(path));
            if (image == null) {
                logger.error("The file: {} could not be opened , it is not an image", path);
            } else {
                valid = true;
            }
        } catch (IOException ex) {
            logger.error("The file: {} could not be opened , it is not an image", path);
        }
        return valid;
    }
}
