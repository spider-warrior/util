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
public class CellToImageTest {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/cell2jpg/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        String docPath = workDir + "新建 XLSX 工作表.xlsx";

        Workbook workbook = new Workbook(docPath);

        ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
        imgOptions.setSaveFormat(SaveFormat.JPG);
        imgOptions.setOnePagePerSheet(true);

        int sheetCount = workbook.getWorksheets().getCount();
        for (int i = 0; i < sheetCount; i++) {
            Worksheet sheet = workbook.getWorksheets().get(i);
            SheetRender sr = new SheetRender(sheet, imgOptions);
            for (int k = 0; k < sr.getPageCount(); k++) {
                // Output the worksheet into Svg image format
                sr.toImage(k, output + sheet.getName() + "-" + k + ".jpg");
            }
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }

}
