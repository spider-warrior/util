package cn.t.util.doc.test.aspose.cell;


import com.aspose.cells.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-01 16:17
 **/
public class CellToPdfTest {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/cell2pdf/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        String docPath = workDir + "cell-1.xlsx";

        Workbook workbook = new Workbook(docPath);
        PdfSaveOptions saveOptions = new PdfSaveOptions();
        saveOptions.setCompliance(PdfCompliance.PDF_A_1_B);
        saveOptions.setAllColumnsInOnePagePerSheet(true);
        saveOptions.setOnePagePerSheet(true);
        workbook.save(output + "result.pdf", saveOptions);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }

}
