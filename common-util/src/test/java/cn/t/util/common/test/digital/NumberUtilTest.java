package cn.t.util.common.test.digital;

import cn.t.util.common.RandomUtil;
import cn.t.util.common.digital.NumberUtil;
import org.junit.Test;

import java.math.BigDecimal;

public class NumberUtilTest {

    @Test
    public void byteToBinaryStringTest() {
        byte num = -5;
        System.out.println(NumberUtil.byteToBinaryStringSimple(num));
        System.out.println(NumberUtil.byteToBinaryStringOriginal(num));
    }

    @Test
    public void shortToBinaryStringTest() {
        short num = -5;
        System.out.println(NumberUtil.shortToBinaryStringSimple(num));
        System.out.println(NumberUtil.shortToBinaryStringOriginal(num));
    }

    @Test
    public void intToBinaryStringTest() {
        //已经修改intToBinaryStringOriginal不再补0，字符串不相等
//        for(int i=0; i<100; i++) {
//            int random = RandomUtil.randomInt(-1000,1000);
//            System.out.println(NumberUtil.intToBinaryStringSimple(random).equals(NumberUtil.intToBinaryStringOriginal(random)));
//        }
        int num = 15;
        System.out.println(NumberUtil.intToBinaryStringSimple(num));
        System.out.println(NumberUtil.intToBinaryStringOriginal(num));
    }

    @Test
    public void longToBinaryStringTest() {
        long num = -5;
        System.out.println(NumberUtil.longToBinaryStringSimple(num));
        System.out.println(NumberUtil.longToBinaryStringOriginal(num));
    }

    @Test
    public void parseFloatTest() {
        byte[] bs1 = {(byte)0xC1, (byte)0x48, (byte)0x00, (byte)0x00};
        byte[] bs2 = {(byte)0x41, (byte)0x8D, (byte)0x00, (byte)0x00};
        byte[] bs3 = {(byte)0xC2, (byte)0xFE, (byte)0xA5, (byte)0x7A};
        byte[] bs4 = {(byte)0x42, (byte)0xFE, (byte)0xA5, (byte)0x7A};
        byte[] bs5 = {(byte)0x3A, (byte)0x37, (byte)0x3D, (byte)0x19};
        byte[] bs6 = {(byte)0xBA, (byte)0x37, (byte)0x3D, (byte)0x19};
        byte[] bs7 = {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] bs8 = {(byte)0x33, (byte)0xD6, (byte)0xBF, (byte)0x95};
        byte[] bs9 = {(byte)0x3E, (byte)0x19, (byte)0x99, (byte)0x9A};
        byte[] bs10 = {(byte)0x3E, (byte)0xCC, (byte)0XCC, (byte)0XCD};
        byte[] bs11 = {(byte)0x3E, (byte)0x00, (byte)0X00, (byte)0X00};
        //-12.5
//        System.out.println(NumberUtil.parseFloat(bs1));
        //17.625
//        System.out.println(NumberUtil.parseFloat(bs2));
        //-127.3232
//        System.out.println(NumberUtil.parseFloat(bs3));
        //127.3232
//        System.out.println(NumberUtil.parseFloat(bs4));
        //0.000699
//        System.out.println(NumberUtil.parseFloat(bs5));
        //-0.000699
//        System.out.println(NumberUtil.parseFloat(bs6));
        //0
//        System.out.println(NumberUtil.parseFloat(bs7));
        //0.0000001
//        System.out.println(NumberUtil.parseFloat(bs8));
        //0.15
//        System.out.println(NumberUtil.parseFloat(bs9));
        //0.4
//        System.out.println(NumberUtil.parseFloat(bs10));
        //0.125
        System.out.println(NumberUtil.parseFloat(bs11));
    }

    @Test
    public void floatRightToBytesTest() {
        System.out.println(NumberUtil.floatRightToBytes(new BigDecimal("0.001953125"), 32));
    }


    @Test
    public void floatToBytesTest() {
        String number1 = "11.235";
        String number2 = "-11.235";
        String number3 = "0.235111";
        String number4 = "-0.235111";
//        11.235
//        01000001001100111100001010001111
//        01000001001100111100001010001111011
        System.out.println(NumberUtil.floatToBytes(number1));
//        -11.235
//        11000001001100111100001010001111
//        11000001001100111100001010001111011
        System.out.println(NumberUtil.floatToBytes(number2));
//        0.235111
//        00111110011100001100000011110000
//        00111110011100001100000011111
        System.out.println(NumberUtil.floatToBytes(number3));

//        -0.235111
//        10111110011100001100000011110000
//        10111110011100001100000011111
        System.out.println(NumberUtil.floatToBytes(number4));
    }

    @Test
    public void tempTest() {
//        System.out.println(0.15 + 0.4 == 0.55);
//        System.out.println(0.2 + 0.4 == 0.6);
//        System.out.println(0.25 + 0.4 == 0.65);
//        System.out.println(1.15+0.4 == 1.15);
//        System.out.println(838860811111111.0125F);
        System.out.println(22.65F);
        System.out.println(22.65F-22);
        System.out.println(BigDecimal.valueOf(22.65d).divideAndRemainder(BigDecimal.ONE)[1].floatValue());
        System.out.println(new BigDecimal("0.99999999961012895744").multiply(new BigDecimal("2")));

    }


    @Test
    public void floatNumberTest() {
        System.out.println(0.3);
        System.out.println(0.6);
        System.out.println(0.6 == 0.2*3);
        System.out.println(0.6 == 0.3*2);
        System.out.println(0.2*3);
        System.out.println(0.3*2);
        System.out.println(100.600000001);

        System.out.println(0.6000000000000001 == 0.6);


        System.out.println(new BigDecimal(0.3).multiply(new BigDecimal(2)).doubleValue());
    }

}
