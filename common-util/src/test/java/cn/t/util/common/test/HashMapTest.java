package cn.t.util.common.test;

import cn.t.util.common.digital.BitUtil;
import cn.t.util.common.digital.ByteUtil;
import cn.t.util.common.digital.IntUtil;

import java.nio.ByteOrder;
import java.util.HashMap;

/**
 * @author yj
 * @since 2020-02-09 19:49
 **/
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("1", "one");
        map.put("2","two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        map.put("6", "six");
        map.put("6", "six");
//        int hash = 267430570;
//        hash = (hash ^ (hash >>> 16));
//        byte[] bytes = IntUtil.intToBytes(hash, ByteOrder.BIG_ENDIAN);
//        for(byte b: bytes) {
//            System.out.println(BitUtil.toUnsignedBitStr(b));
//        }
    }
}
