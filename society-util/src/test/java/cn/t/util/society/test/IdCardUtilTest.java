package cn.t.util.society.test;

import cn.t.util.society.IdCardUtil;
import org.junit.Test;

public class IdCardUtilTest {

    /**
     * 计算身份证校验码
     */
    @Test
    public void testCalculateIdCardCheckSum() {
        String idCardNumber = "130627198910052853";
        char checkSum = IdCardUtil.calculateCheckSum(idCardNumber.toCharArray());
        System.out.println("result: " + checkSum);
    }

    /**
     * 验证身份证有效性
     */
    @Test
    public void testValidIdCard() {
        String idCardNumber = "130627198910052853";
        System.out.println(IdCardUtil.isValid(idCardNumber));
    }
}
