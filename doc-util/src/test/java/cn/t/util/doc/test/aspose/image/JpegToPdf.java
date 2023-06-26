package cn.t.util.doc.test.aspose.image;


import com.aspose.pdf.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JpegToPdf {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/jpeg2pdf/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        try(Document document = new Document()) {
            List<String> imagePathList = new ArrayList<>();
            imagePathList.add("D:/tmp/jpeg2pdf/0.jpg");
            imagePathList.add("D:/tmp/jpeg2pdf/1.jpg");
            imagePathList.add("D:/tmp/jpeg2pdf/2.jpg");
            imagePathList.add("D:/tmp/jpeg2pdf/3.jpg");
            imagePathList.add("D:/tmp/jpeg2pdf/4.jpg");
            imagePathList.add("D:/tmp/jpeg2pdf/5.jpg");
            for (String imagePath : imagePathList) {
                Page page = document.getPages().add();
                Image image = new Image();
                image.setFile(imagePath);
                image.setMargin(new MarginInfo(0, 0, 0, 0));
                page.getPageInfo().setMargin(new MarginInfo(0, 0, 0, 0));
                page.getParagraphs().add(image);
            }
            document.save(output + "result.pdf");
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }
}
