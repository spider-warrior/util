package cn.t.util.doc.test.aspose.pdf;


import com.aspose.pdf.DocSaveOptions;
import com.aspose.pdf.Document;
import com.aspose.pdf.License;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToWord {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/aspose/pdf2word/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

//        String docPath = workDir + "2021爱分析药企数字化趋势报告.pdf";
        String docPath = workDir + "BQD-D02300047 2021(APP)桥(门)式起重机定期检验记录 - 副本.pdf";

//        FontRepository.getSources();
//        //加载字体文件
//        FontRepository.addLocalFontPath("C:/Windows/Fonts/");
//        FontRepository.addLocalFontPath("D:/workspace/font-set/fangzheng/");
//        FontRepository.addLocalFontPath("D:/workspace/font-set/yanyizi/");
//        FontRepository.loadFonts();
//        FontRepository.setReplaceNotFoundFonts(true);
//        FontRepository.getSources();

//        FontRepository.setReplaceNotFoundFonts(true);
//        List<String> pathList = new ArrayList<>();
//        pathList.add("C:/Windows/Fonts");
//        pathList.add("D:/workspace/font-set/fangzheng");
//        pathList.add("D:/workspace/font-set/yanyizi");
//        FontRepository.setLocalFontPaths(pathList);
//        FontRepository.reloadFonts();

        loadLicense(licenseFilePath);

        // Load the document from disk.
        Document doc = new Document(Files.newInputStream(Paths.get(docPath)));
        DocSaveOptions saveOptions = new DocSaveOptions();
        saveOptions.setFormat(DocSaveOptions.DocFormat.DocX);
        doc.save(output + "result.docx", saveOptions);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License asposeLicense = new License();
        asposeLicense.setLicense(is);
        System.out.println(asposeLicense);
    }

}
