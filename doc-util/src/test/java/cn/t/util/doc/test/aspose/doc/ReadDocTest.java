package cn.t.util.doc.test.aspose.doc;


import com.aspose.words.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadDocTest {
    public static void main(String[] args) throws Exception {
        String workDir = "C:/Users/36296/Desktop/";
        String licenseFilePath = "D:/tmp/word2html/license.xml";

        loadLicense(licenseFilePath);

        String docPath = workDir + "测试文档.docx";
        // Load the document from disk.
        Document doc = new Document(Files.newInputStream(Paths.get(docPath)));

        // Get the comments in the document.
        NodeCollection<Comment> comments = doc.getChildNodes(NodeType.COMMENT, true);
        for (int i = 0; i < comments.getCount(); i++) {
            Comment comment = (Comment)comments.get(i);
            CommentRangeStart rangeStart = (CommentRangeStart)doc.getChild(NodeType.COMMENT_RANGE_START, i,true);
            StringBuilder builder = new StringBuilder(rangeStart.getText());
            Node current = rangeStart;
            while (true) {
                if(current.getNodeType() == NodeType.COMMENT_RANGE_END) {
                    break;
                }
                current = current.nextPreOrder(doc);
                builder.append(current.getText());
            }
            System.out.printf("text: %s, label: %s%n", builder.toString(), comment.getText());
        }
    }

    private static void loadLicense(String licenseFilePath) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(licenseFilePath));
        License aposeLic = new License();
        aposeLic.setLicense(is);
        System.out.println(aposeLic);
    }

}
