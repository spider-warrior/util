package cn.t.util.doc.test;

import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.DefaultResourceCache;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-06-17 10:00
 **/
public class PdfUtilTest {
    public static void main(String[] args) throws Exception {
        String sourceDir = "D:/tmp/pdftest/210207-人力资源数字化趋势报告20210419.pdf"; // Pdf files are read from this folder
        String destinationDir = "D:/tmp/pdftest/Converted_PdfFiles_to_Image/"; // converted images from pdf document are saved here

        File sourceFile = new File(sourceDir);
        File destinationFile = new File(destinationDir);
        if (!destinationFile.exists()) {
            boolean success = destinationFile.mkdir();
            if(!success) {
                throw new RuntimeException("目标文件地址初始化失败");
            }
            System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
        }
        if (sourceFile.exists()) {
            System.out.println("Images copied to Folder: " + destinationFile.getName());
            PDDocument document = PDDocument.load(sourceFile, MemoryUsageSetting.setupTempFileOnly());
            document.setResourceCache(new DefaultResourceCache() {
                @Override
                public void put(COSObject indirect, PDXObject xObject) {}
            });
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();
            System.out.println("Total pages to be converted -> " + pageCount);
            String fileName = sourceFile.getName().replace(".pdf", "");
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 200, ImageType.RGB);
                File outputFile = new File(destinationDir + fileName + "_" + i +".png");
                System.out.println("Image Created -> " + outputFile.getName());
                ImageIO.write(image, "png", outputFile);
            }
            document.close();
            System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
        } else {
            System.err.println(sourceFile.getName() + " File not exists");
            }

    }
}
