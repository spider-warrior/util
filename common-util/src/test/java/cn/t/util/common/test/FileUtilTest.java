package cn.t.util.common.test;

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
}
