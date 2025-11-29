package cn.t.util.doc.test.aspose.pdf;


import cn.t.util.common.RandomUtil;
import com.aspose.pdf.*;

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
        addWaterMark(doc);
        DocSaveOptions saveOptions = new DocSaveOptions();
        saveOptions.setFormat(DocSaveOptions.DocFormat.DocX);
        doc.save(output + "result.docx", saveOptions);
    }

//    private static void addWaterMark(Document doc) {
//        com.aspose.pdf.TextStamp textStamp = new com.aspose.pdf.TextStamp("hello");
//        textStamp.setBackground(true); // 设置为背景
//        textStamp.setOpacity(0.25);     // 设置透明度
//        textStamp.setRotateAngle(45);  // 设置旋转角度
//        textStamp.getTextState().setFontSize(30); // 设置字体大小
//        textStamp.setHorizontalAlignment(com.aspose.pdf.HorizontalAlignment.Center);
//        textStamp.setVerticalAlignment(com.aspose.pdf.VerticalAlignment.Center);
//        for (com.aspose.pdf.Page pdfPage : doc.getPages()) {
//            pdfPage.addStamp(textStamp);
//        }
//    }

    private static void addWaterMark(Document doc) {
        for (com.aspose.pdf.Page pdfPage : doc.getPages()) {
            double pageWidth = pdfPage.getRect().getWidth();
            double pageHeight = pdfPage.getRect().getHeight();
            // 定义平铺的间距 (根据实际效果调整间隔)
            // 间距越小，水印越密集
            double xInterval = 200;
            double yInterval = 150;
            for (double y = RandomUtil.randomInt(0, 100); y < pageHeight; y += yInterval) {
                for (double x = RandomUtil.randomInt(0, 100); x < pageWidth; x += xInterval) {
                    com.aspose.pdf.TextStamp textStamp = new com.aspose.pdf.TextStamp("hello");
                    textStamp.setBackground(true); // 设置为背景
                    textStamp.setOpacity(0.25);     // 设置透明度
                    textStamp.setRotateAngle(45);  // 设置旋转角度
                    textStamp.getTextState().setFontSize(30); // 设置字体大小
                    textStamp.setHorizontalAlignment(HorizontalAlignment.None);
                    textStamp.setVerticalAlignment(VerticalAlignment.None);
                    // 设置具体坐标
                    textStamp.setXIndent(x);
                    textStamp.setYIndent(y);
                    pdfPage.addStamp(textStamp);
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
