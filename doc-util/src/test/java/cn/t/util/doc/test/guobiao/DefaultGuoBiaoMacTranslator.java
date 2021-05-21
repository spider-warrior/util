package cn.t.util.doc.test.guobiao;

import cn.t.util.common.digital.ShortUtil;

import java.nio.ByteOrder;
import java.util.Arrays;

public class DefaultGuoBiaoMacTranslator implements GuoBiaoMacTranslator {

    @Override
    public String translate(String controllerId, String huiluNo, String deviceNo) {
        byte[] deviceAddressBytes = ShortUtil.shortToBytes(Short.parseShort(huiluNo), ByteOrder.LITTLE_ENDIAN);
        byte[] huiLuBytes = ShortUtil.shortToBytes(Short.parseShort(deviceNo), ByteOrder.LITTLE_ENDIAN);
        byte[] macBytes = Arrays.copyOf(huiLuBytes, huiLuBytes.length + deviceAddressBytes.length);
        System.arraycopy(deviceAddressBytes, 0, macBytes, huiLuBytes.length, deviceAddressBytes.length);
        StringBuilder builder = new StringBuilder();
        for(byte b: macBytes) {
            builder.append(b&0xFF).append('.');
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
