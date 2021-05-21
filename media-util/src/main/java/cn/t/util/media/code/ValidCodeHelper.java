package cn.t.util.media.code;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidCodeHelper {

    private static final Pattern p = Pattern.compile("\\s*|\t|\r|\n");
    private final Tesseract instance = new Tesseract();

    public ValidCodeHelper(String path) {
        instance.setDatapath(path);
    }

    public String analyseImageCode(File image) throws TesseractException {
        if (image == null || !image.exists()) {
            return null;
        }
        Matcher m = p.matcher(instance.doOCR(image));
        return m.replaceAll("");
    }
}
