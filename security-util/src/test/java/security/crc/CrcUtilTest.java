package security.crc;

import cn.t.util.common.digital.HexUtil;
import cn.t.util.security.message.crc.CrcUtil;
import org.junit.Test;

public class CrcUtilTest {


    @Test
    public void crc16Test() throws IllegalAccessException {
//        byte[] bytes = {
//            0x01,
//            0x03,
//            0x00,
//            0x10,
//            0x00,
//            0x02
//        };
        byte[] bytes = "abc".getBytes();
        short result = CrcUtil.crc16Modbus(bytes);
        System.out.println(result);
        System.out.println(HexUtil.numberToHexString(result));
    }


}
