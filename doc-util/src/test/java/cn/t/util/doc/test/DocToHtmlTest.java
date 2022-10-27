package cn.t.util.doc.test;


import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
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
        String docPath = workDir + "2022爱分析･银行数字化实践报告（规范版）v4-20220712.docx";
        // Load the document from disk.
        Document doc = new Document(new FileInputStream(docPath));
        doc.save(output + "Document_out.html", SaveFormat.HTML);
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = new FileInputStream(licenseFilePath);
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }
}
