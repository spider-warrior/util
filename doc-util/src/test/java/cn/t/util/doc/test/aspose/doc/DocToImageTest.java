package cn.t.util.doc.test.aspose.doc;


import com.aspose.words.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-01 16:17
 **/
public class DocToImageTest {
    public static void main(String[] args) throws Exception {
        String workDir = "D:/tmp/word2html/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

        String docPath = workDir + "2023爱分析･生成式AI应用实践报告-0615.docx";
        // Load the document from disk.
        Document doc = new Document(Files.newInputStream(Paths.get(docPath)));

//        doc.save(output + "result.jpg", SaveFormat.JPEG);
        ImageSaveOptions imageSaveOptions = new ImageSaveOptions(SaveFormat.JPEG);
        imageSaveOptions.setPageSet(new PageSet(new PageRange(0, doc.getPageCount() - 1)));
        imageSaveOptions.setPageSavingCallback(new HandlePageSavingCallback(output));
        doc.save(output + "result.jpg", imageSaveOptions);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }

    private static class HandlePageSavingCallback implements IPageSavingCallback {
        private final String outputDir;

        public void pageSaving(PageSavingArgs args) {
            args.setPageFileName(MessageFormat.format(outputDir + "Page_{0}.png", args.getPageIndex()));
        }

        public HandlePageSavingCallback(String outputDir) {
            this.outputDir = outputDir;
        }
    }
}
