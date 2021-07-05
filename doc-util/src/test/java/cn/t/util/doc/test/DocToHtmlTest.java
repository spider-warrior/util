package cn.t.util.doc.test;

import com.aspose.words.CrackedLicense216;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-01 16:17
 **/
public class DocToHtmlTest {
    public static void main(String[] args) throws Exception {

        String workDir = "D:/tmp/word2html/";
        String output = workDir + "output/";
        String licenseFilePath = workDir + "license.xml";

        loadLicense(licenseFilePath);

//        String docPath = workDir + "20210524-采购数字化厂商全景报告.docx";
        String docPath = workDir + "购物中心数字化趋势报告20210621.docx";
        // Load the document from disk.
        Document doc = new Document(docPath);
        doc.save(output + "Document_out.html", SaveFormat.HTML);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = new FileInputStream(licenseFilePath);
        License aposeLic = new CrackedLicense216();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }
}
