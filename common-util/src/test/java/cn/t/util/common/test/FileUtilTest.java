package cn.t.util.common.test;

import cn.t.util.common.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileUtilTest {

    @Test
    public void renameFileNameByFeature() {
        String dirPath = "D:\\tmp\\verify-code\\source-image";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if(files != null && files.length > 0) {
            for (File file : files) {
                String fileName = file.getName().replace(" (", "-").replace(")", "");
                boolean success = file.renameTo(new File(dirPath + "\\" + fileName));
                if(!success) {
                    System.out.println("文件重命名失败");
                }
            }
        }
    }

    @Test
    public void renameFileNameByIndex() {
        String dirPath = "D:\\tmp\\verify-code\\source-image";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if(files != null && files.length > 0) {
            int index = 0;
            for (File file : files) {
                boolean success = file.renameTo(new File(dirPath + "\\" + String.format("img-%d.jpg", index++)));
                if(!success) {
                    System.out.println("文件重命名失败");
                }
            }
        }
    }

    @Test
    public void extractFileNameTest() {
        String fileName1 = null;
        String fileName2 = "";
        String fileName3 = "abc";
        String fileName4 = "abc.png";
        String fileName5 = "abc.a.b.c.png";
        System.out.println(FileUtil.extractFileName(fileName1));
        System.out.println(FileUtil.extractFileName(fileName2));
        System.out.println(FileUtil.extractFileName(fileName3));
        System.out.println(FileUtil.extractFileName(fileName4));
        System.out.println(FileUtil.extractFileName(fileName5));
    }
}
