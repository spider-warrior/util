package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.PageCollection;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToImage {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/pdf2jpeg/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        String docPath = workDir + "2021爱分析药企数字化趋势报告.pdf";

        loadLicense(licenseFilePath);

        Document doc = new Document(Files.newInputStream(Paths.get(docPath)));
        PageCollection pageCollection = doc.getPages();
        JpegDevice device = new JpegDevice(new Resolution(300));
        for (int i = 0; i < pageCollection.size(); i++) {
            device.process(pageCollection.get_Item(i+1), output + i + ".jpg");
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
