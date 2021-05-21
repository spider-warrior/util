package cn.t.util.doc.test;

import cn.t.util.common.RandomUtil;
import cn.t.util.doc.ExcelUtil;
import cn.t.util.io.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yj
 * @since 2020-02-08 14:41
 **/
public class ExcelUtilTest {

    @Test
    public void exportWorkbookTest() throws IOException {
        List<Object> userList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            User user = new User();
            user.setName(RandomUtil.randomString(200));
            user.setSex(String.valueOf(RandomUtil.randomInt(0,2) == 1));
            user.setAge(String.valueOf(i));
            userList.add(user);
        }
        List<String> headerList = new ArrayList<>();
        headerList.add("姓名");
        headerList.add("性别");
        headerList.add("年龄");
        List<String> keyList = new ArrayList<>();
        keyList.add("name");
        keyList.add("sex");
        keyList.add("age");
        try (
            Workbook workbook = ExcelUtil.exportWorkbook("用户报表", headerList, keyList, userList)
        ) {
            String filePath = FileUtil.appendFilePath(FileUtil.getProjectPath(), "用户.xlsx");
            workbook.write(new FileOutputStream(filePath));
        }

    }
}
