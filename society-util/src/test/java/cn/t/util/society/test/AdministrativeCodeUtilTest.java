package cn.t.util.society.test;

import cn.t.util.common.LoggerUtil;
import cn.t.util.society.area.AdministrativeCodeUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;

public class AdministrativeCodeUtilTest {

    @Test
    public void generateAdministrativeCodeTest() throws IOException, InvalidFormatException {
        LoggerUtil.setJdkRootLoggerLevel(Level.WARNING);
        String path = "/home/amen/ideaworkspace/my/util/society-util/src/main/resources/area-code.xls";
        AdministrativeCodeUtil.generateAdministrativeCode(path);
    }
}
