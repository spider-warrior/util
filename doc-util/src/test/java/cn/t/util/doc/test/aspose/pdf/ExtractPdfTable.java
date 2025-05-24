package cn.t.util.doc.test.aspose.pdf;

import com.aspose.pdf.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractPdfTable {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/aspose/extractpdftable/";
        String licenseFilePath = workDir + "license.xml";
        String docPath = workDir + "BQD-D02300047 2021(APP)桥(门)式起重机定期检验记录 - 副本.pdf";
        loadLicense(licenseFilePath);

        com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(docPath);
        com.aspose.pdf.TableAbsorber absorber = new com.aspose.pdf.TableAbsorber();

        // Scan pages
        for (com.aspose.pdf.Page page : pdfDocument.getPages()) {
            absorber.visit(page);
            for (com.aspose.pdf.AbsorbedTable table : absorber.getTableList()) {
                System.out.println("Table-----------------------------------------");
                // Iterate throught list of rows
                for (com.aspose.pdf.AbsorbedRow row : table.getRowList()) {
                    // Iterate throught list of cell
                    for (com.aspose.pdf.AbsorbedCell cell : row.getCellList()) {
                        for (com.aspose.pdf.TextFragment fragment : cell.getTextFragments()) {
                            StringBuilder sb = new StringBuilder();
                            for (com.aspose.pdf.TextSegment seg : fragment.getSegments())
                                sb.append(seg.getText());
                            System.out.print(sb + "|");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }
}
