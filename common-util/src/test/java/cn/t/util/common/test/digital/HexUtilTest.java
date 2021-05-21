package cn.t.util.common.test.digital;

import cn.t.util.common.digital.HexUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class HexUtilTest {

    @Test
    public void testEncodeToString() {
//        String str = "123456789";
        String str = "POST";
        System.out.println(HexUtil.bytesToHex(str.getBytes()));
    }

    @Test
    public void testDecodeToBytes() {
//        String hex = "005e040165640700025631315907000b56312e3417013a38393836303431323130313834303435343932380904041910311708290504023840050401000a05013c110005010007d005010100000502050320050206008c05001900047b6b";
        String hex = "c9bb3fae6f12500fbbcc5088272ce72dd0cbdb0c3fc444be95ba344aac9ed034edf686b5d31b9635d84f775dd55e85b14784b88bba96df0b5482246e0ae565a2dad4d3bb3c16da3f4e88e241f72cd02458e5bc19e432d2bb29ed546ff4a2d0385066698b2ee1bfaef3049510fa0825c3f3d50e66c5e5f7b08fff9f0db395ee8263c12ea545ae22f2825af5e4c3d1cc53";
        System.out.println(new String(HexUtil.stringToBytes(hex)));
    }

    @Test
    public void guiLinCheckSumTest() throws IllegalAccessException {
//        String data = "C419151840264657:0811KPA:3.5V:0100";
//        String data = "C2B0006963106758:0750KPA:3.7V:0000";
        String data = "MU:KPA:1#0750:2#0755:3#0755:47#0800:";
        System.out.println(data);
        byte checksum = 0;
        for (char c : data.toCharArray()) {
            checksum += c;
        }
        System.out.println(HexUtil.numberToHexString(checksum));
    }

    @Test
    public void numberToHexStringTest() throws IllegalAccessException {
//        for(byte i=0; i<Byte.MAX_VALUE; i++) {
//            System.out.println(HexUtil.numberToHexString(i));
//            System.out.println("-------------------------------------------------");
//        }

//        for(short i=Short.MAX_VALUE - Byte.MAX_VALUE; i < Short.MAX_VALUE; i++) {
//            System.out.println(i);
//            System.out.println(HexUtil.numberToHexString(i));
//            System.out.println("-------------------------------------------------");
//        }

//        for(char i=Character.MAX_VALUE - Byte.MAX_VALUE; i < Character.MAX_VALUE; i++) {
//            System.out.println(i);
//            System.out.println(HexUtil.numberToHexString(i));
//            System.out.println("-------------------------------------------------");
//        }

//        for(int i=Integer.MAX_VALUE - Byte.MAX_VALUE; i < Integer.MAX_VALUE; i++) {
//            System.out.println(i);
//            System.out.println(HexUtil.numberToHexString(i));
//            System.out.println("-------------------------------------------------");
//        }

        for (long i = Long.MAX_VALUE - Byte.MAX_VALUE; i < Long.MAX_VALUE; i++) {
            System.out.println(i);
            System.out.println(HexUtil.numberToHexString(i));
            System.out.println("-------------------------------------------------");
        }

    }

    @Test
    public void recoverHex() throws UnsupportedEncodingException {
//        String hex = "80004d12000300017012000300026374040000040000026d7304000f42400002746b08002033303966396130313466303965323566333530333033386464306137623634380001610300000001630200";
        String hex = "8000c2120003000163020100016103000d0001701200030001630800096d61696c2e73656e6400017204ffffffff000170120007000b636d644261736554696d6508000a31353634363334363933000a616c6c69616e63654964080000000974617267657455696408000f33343835303733323130303035333700057469746c650800000008636f6e74656e74730800053131313131000d73656e644c6f63616c54696d6508000a3135363436333436393200046e616d6508000ae596b55e5f5ee596b532";
        System.out.println(new String(HexUtil.stringToBytes(hex), "iso-8859-1"));
    }

}
