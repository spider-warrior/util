package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import com.aspose.pdf.License;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToExcel {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/aspose/pdf2excel/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        String docPath = workDir + "BQD-D02300047 2021(APP)桥(门)式起重机定期检验记录 - 副本.pdf";

        loadLicense(licenseFilePath);

        try (Document doc = new Document(docPath)) {
            ExcelSaveOptions options = new ExcelSaveOptions();
            options.setFormat(ExcelSaveOptions.ExcelFormat.XLSX);
            doc.save(output + "result.xlsx", options);
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
