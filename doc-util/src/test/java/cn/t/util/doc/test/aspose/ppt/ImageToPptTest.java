package cn.t.util.doc.test.aspose.ppt;


import com.aspose.slides.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImageToPptTest {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/aspose/jpeg2ppt/";
        String output = workDir;
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        List<String> imagePathList = new ArrayList<>();
        imagePathList.add("D:/tmp/aspose/jpeg2ppt/jpeg/Page0001.jpg");
        imagePathList.add("D:/tmp/aspose/jpeg2ppt/jpeg/Page0002.jpg");
        imagePathList.add("D:/tmp/aspose/jpeg2ppt/jpeg/Page0003.jpg");
        imagePathList.add("D:/tmp/aspose/jpeg2ppt/jpeg/Page0004.jpg");

        Presentation presentation = new Presentation();

        for (String imagePath : imagePathList) {
            // 添加一个新的幻灯片
            ISlide slide = presentation.getSlides().addEmptySlide(presentation.getLayoutSlides().get_Item(0));
            // 从文件中读取图片
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            IPPImage image = presentation.getImages().addImage(imageBytes);
            // 将图片添加到幻灯片的中央
            IPictureFrame pictureFrame = slide.getShapes().addPictureFrame(ShapeType.Rectangle, 0, 0,
                (float) presentation.getSlideSize().getSize().getWidth(),
                (float) presentation.getSlideSize().getSize().getHeight(), image);
            // 设置旋转角度为90度
            pictureFrame.setRotation(-90.0f);
        }

        presentation.save(output + "result.pptx", SaveFormat.Pptx);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }
}
