package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.TextAbsorber;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToTxt {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/pdf2txt/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        String docPath = workDir + "张赵洁.pdf";


        loadLicense(licenseFilePath);

        Document document = new Document(docPath);
        TextAbsorber textAbsorber = new TextAbsorber();
        document.getPages().accept(textAbsorber);
        String extractedText = textAbsorber.getText();
        java.io.FileWriter writer = new java.io.FileWriter(output + "Extracted_text.txt");
        writer.write(extractedText);
        writer.close();
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
