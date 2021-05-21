package cn.t.util.media.test;

import com.asprise.ocr.Ocr;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ValidCodeHelperTest {

    /**
     * 克隆https://github.com/tesseract-ocr/tesseract
     * 下载训练文件地址https://tesseract-ocr.github.io/tessdoc/Data-Files.html
     */
    @Test
    public void tess4j() throws TesseractException {
        // JNA Interface Mapping
        ITesseract instance = new Tesseract();
        // JNA Direct Mapping
        //ITesseract instance = new Tesseract1();
        instance.setTessVariable("user_defined_dpi", "200");
        instance.setDatapath("D:/workspace/third-party/tessdata-master");
        //这样就能使用classpath目录下的训练库了
        //File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        //英文库识别数字比较准确
        instance.setLanguage("eng");
        String code = instance.doOCR(new File("C:/Users/36296/AppData/Local/Temp/1604924855410.jpg"));
        System.out.println(code);
    }

    @Test
    public void ocr() {
        Ocr.setUp(); // one time setup
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        String code = ocr.recognize(new File[] {new File("C:/Users/36296/AppData/Local/Temp/1604924855410.jpg")},
            Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
        System.out.println(code);
    }

    @Test
    public void tujian() throws IOException {
        //用户名
        String username= "qq362961910";
        //密码
        String password= "yangjian";
        //验证码类型(默认数英混合),1:纯数字, 2:纯英文，3:数英混合：可空
        String typeid="3";
        //备注字段: 可以不写
        String remark="输出计算结果";
        InputStream inputStream;
        //你需要识别的1:图片地址，2:也可以是一个文件
        //1:这是远程url的图片地址
        //String url = "https://ningge.oss-cn-shanghai.aliyuncs.com/recordImage/0000008bd2134152aa5fad036a802a89.jpg";
        //URL u = new URL(url);
        //inputStream=u.openStream();
        //2:这是本地文件
        File needRecoImage=new File("C:\\Users\\36296\\AppData\\Local\\Temp\\1604927645066.jpg");
        inputStream=new FileInputStream(needRecoImage);

        Map< String, String> data = new HashMap<>();
        data.put("username",username);
        data.put("password", password);
        data.put("typeid", typeid);
        data.put("remark", remark);

        String resultString = Jsoup.connect("http://api.ttshitu.com/create.json")
            .data(data).data("image","test.jpg",inputStream)
            .ignoreContentType(true)
            .post().text();
        System.out.println(resultString);
    }

}
