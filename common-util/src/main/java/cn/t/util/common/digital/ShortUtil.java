package cn.t.util.common.digital;

import java.nio.ByteOrder;

public class ShortUtil {

    public static byte[] shortToBytes(short value, ByteOrder byteOrder) {
        byte[] bytes = new byte[2];
        if(ByteOrder.LITTLE_ENDIAN == byteOrder) {
            //小端
            bytes[0] = (byte) value;
            bytes[1] = (byte) (value >> 8);
        } else {
            //大端
            bytes[0] = (byte) (value >> 8);
            bytes[1] = (byte) value;
        }
        return bytes;
    }

    public static short bytesToShort(byte[] bs) {
        return bytesToShort(bs, ByteOrder.BIG_ENDIAN);
    }

    public static short bytesToShort(byte[] bs, ByteOrder byteOrder) {
        return bytesToShort(bs, 0, byteOrder);
    }


    public static short bytesToShort(byte[] source, int offset, ByteOrder order) {
        short value;
        if (ByteOrder.LITTLE_ENDIAN == order) {
            //小端
            value = (short)(((source[offset] & 0xFF))
                | ((source[offset + 1] & 0xFF) << 8));
        } else {
            //大端
            value = (short)(((source[offset] & 0xFF) << 8)
                | (source[offset + 1] & 0xFF)) ;
        }
        return value;
    }
}
