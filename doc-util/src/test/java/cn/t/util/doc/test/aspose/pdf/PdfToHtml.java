package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlSaveOptions;
import com.aspose.pdf.LettersPositioningMethods;
import com.aspose.pdf.License;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToHtml {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/aspose/pdf2html/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        String docPath = workDir + "BQD-D02300047 2021(APP)桥(门)式起重机定期检验记录 - 副本.pdf";

        loadLicense(licenseFilePath);

        try (Document doc = new Document(docPath)) {
            HtmlSaveOptions options = new HtmlSaveOptions();
//            options.setPartsEmbeddingMode(HtmlSaveOptions.PartsEmbeddingModes.EmbedAllIntoHtml);
//
//            options.setLettersPositioningMethod(LettersPositioningMethods.UseEmUnitsAndCompensationOfRoundingErrorsInCss);
//            options.setRasterImagesSavingMode(HtmlSaveOptions.RasterImagesSavingModes.AsEmbeddedPartsOfPngPageBackground);
//            options.setFontSavingMode(HtmlSaveOptions.FontSavingModes.SaveInAllFormats);
            doc.save(output + "result.html", options);
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
