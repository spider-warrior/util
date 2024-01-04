package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.PptxSaveOptions;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToPpt {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/pdf2ppt/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";
        String docPath = workDir + "待转换.pdf";

        loadLicense(licenseFilePath);

        // Load the document from disk.
        Document doc = new Document(Files.newInputStream(Paths.get(docPath)));
        PptxSaveOptions saveOptions = new PptxSaveOptions();
        doc.save(output + "result.pptx", saveOptions);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
