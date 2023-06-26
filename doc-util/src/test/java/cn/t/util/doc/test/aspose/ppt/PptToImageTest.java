package cn.t.util.doc.test.aspose.ppt;


import com.aspose.cells.License;
import com.aspose.slides.ISlide;
import com.aspose.slides.ISlideCollection;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-01 16:17
 **/
public class PptToImageTest {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/ppt2image/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        String docPath = workDir + "中国非结构化数据中台实践白皮书.pptx";

        Presentation presentation = new Presentation(docPath);
        ISlideCollection slideCollection = presentation.getSlides();
        for (ISlide iSlide : slideCollection) {
            BufferedImage bmp = iSlide.getThumbnail(1f, 1f);
            ImageIO.write(bmp, "JPEG", new java.io.File(output + String.format("slide_%d.jpg", iSlide.getSlideNumber())));
        }
        presentation.dispose();
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }

}
