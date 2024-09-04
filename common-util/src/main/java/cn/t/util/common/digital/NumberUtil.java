package cn.t.util.common.digital;

import cn.t.util.common.ArrayUtil;
import cn.t.util.common.StringUtil;

import java.math.BigDecimal;

public final class NumberUtil {

    public static String byteToBinaryStringSimple(byte num) {
        StringBuilder stringBuilder = new StringBuilder(8);
        for(int i=7; i>=0; i--) {
            stringBuilder.append(num>>i&1);
        }
        return stringBuilder.toString();
    }

    public static String byteToBinaryStringOriginal(byte num) {
        int numToUse = num > 0 ? num : -num;
        numToUse = ~numToUse;
        numToUse++;
        StringBuilder stringBuilder = new StringBuilder(8);
        for(int i=7; i>=0; i--) {
            stringBuilder.append(numToUse>>i&1);
        }
        return stringBuilder.toString();
    }
    public static String shortToBinaryStringSimple(short num) {
        StringBuilder stringBuilder = new StringBuilder(16);
        for(int i=15; i>=0; i--) {
            stringBuilder.append(num>>i&1);
        }
        return stringBuilder.toString();
    }

    public static String shortToBinaryStringOriginal(short num) {
        int numToUse = num > 0 ? num : -num;
        numToUse = ~numToUse;
        numToUse++;
        StringBuilder stringBuilder = new StringBuilder(16);
        for(int i=15; i>=0; i--) {
            stringBuilder.append(numToUse>>i&1);
        }
        return stringBuilder.toString();
    }

    public static String intToBinaryStringSimple(int num) {
        StringBuilder stringBuilder = new StringBuilder(32);
        for(int i=0; i<32 && num!=0; i++) {
            stringBuilder.insert(0, num&1);
            num>>=1;
        }
        return stringBuilder.toString();
    }
    public static String intToBinaryStringOriginal(int num) {
        boolean positive = num >= 0;
        if(num < 0) {
            num = -num;
            positive = false;
        }
        StringBuilder builder = new StringBuilder(32);
        for(int i=0; i<31; i++) {
            int quotient = num/2;
            int remainder = num%2;
            if(remainder == 0) {
                builder.insert(0, "0");
            } else if(remainder == 1) {
                builder.insert(0, "1");
            } else {
                //never run this code
                throw new RuntimeException("what the fuck");
            }
            if(quotient == 0) {
                break;
            }
            num = quotient;
        }
        if(!positive && builder.length() < 32) {
            int missing = 32-builder.length();
            for(int i=0; i<missing; i++) {
                builder.insert(0, "0");
            }
        }
        if(positive) {
            return builder.toString();
        } else {
            for(int i=0; i<builder.length(); i++) {
                if('0' == builder.charAt(i)) {
                    builder.setCharAt(i, '1');
                } else {
                    builder.setCharAt(i, '0');
                }
            }
            for(int i=builder.length()-1; i>=0; i--) {
                if('0' == builder.charAt(i)) {
                    builder.setCharAt(i, '1');
                    break;
                } else {
                    builder.setCharAt(i, '0');
                }
            }
            return builder.toString();
        }
    }

    public static String negativeIntToBinaryStringOriginal(int num) {
        int numToUse = num > 0 ? num : -num;
        numToUse = ~numToUse;
        return intToBinaryStringOriginal(++numToUse);
    }




    public static String longToBinaryStringSimple(long num) {
        StringBuilder stringBuilder = new StringBuilder(64);
        for(long i=63; i>=0; i--) {
            stringBuilder.append(num>>i&1);
        }
        return stringBuilder.toString();
    }

    public static String longToBinaryStringOriginal(long num) {
        long numToUse = num > 0 ? num : -num;
        numToUse = ~numToUse;
        numToUse++;
        StringBuilder stringBuilder = new StringBuilder(64);
        for(long i=63; i>=0; i--) {
            stringBuilder.append(numToUse>>i&1);
        }
        return stringBuilder.toString();
    }

    //参考地址
    //https://wenku.baidu.com/view/d7dc810116fc700abb68fcef.html
    public static float parseFloat(byte[] data) {
        if(ArrayUtil.isEmpty(data) || data.length !=4 ) {
            throw new IllegalArgumentException("数组的长度必须为4");
        }
        //是否为负数
        boolean negative = (data[0] & 0x80) == 0x80;
        //8位
        int e = (data[0] << 1)&0xFF;
        byte eTail = (byte)((data[1]>>>7)&1);
        e|=eTail;
        if(e == 0) {
            data[1]&=((byte)0x7F);
        } else if(e == 0xFF) {

        } else {
            e-=127;
            data[1]|=((byte)0x80);
        }
        //dot
        int totalMove = e;
        //把float值拆分成左边的整数和右边的小数
        int left = 0;
        float right = 0;
        //小数右移
        if(totalMove > 0) {
            //当前比特位索引
            int movedForLeftCount = 0;
            int movedForRightCount = 0;
            for(int i=1; i<4; i++) {
                byte currentByte = data[i];
                for(int k=0; k<8; k++) {
                    //获取当前需要计算的bit
                    byte afterMovedValue = (byte)(currentByte >>> (7 - k));
                    byte bitV = (byte)(afterMovedValue & (byte)0b00000001);
                    //计算整数
                    if(movedForLeftCount <= totalMove) {
                        //左移一位
                        left<<=1;
                        if(bitV == 1) {
                            left|=bitV;
                        }
                        movedForLeftCount++;
                    } else {
                        if(bitV == 1){
                            //计算小数
                            right+=((float)(bitV)/(2L << movedForRightCount));
                        }
                        movedForRightCount++;
                    }
                }
            }

        } else if(totalMove == 0){
            //特殊情况
            for(int i=1; i<4; i++) {
                byte currentByte = data[i];
                for(int k=0; k<8; k++) {
                    //获取当前需要计算的bit
                    byte afterMovedValue = (byte)(currentByte >>> (7 - k));
                    byte bitV = (byte)(afterMovedValue & (byte)1);
                    if(bitV == 1){
                        //计算小数
                        right+=((float)(bitV)/(2L << ((i*8) + k)));
                    }
                }
            }

        } else {
            int movedForRightCount = -(totalMove+1);
            for(int i=1; i<4; i++) {
                byte currentByte = data[i];
                for(int k=0; k<8; k++) {
                    //获取当前需要计算的bit
                    byte afterMovedValue = (byte)(currentByte >>> (7 - k));
                    byte bitV = (byte)(afterMovedValue & (byte)1);
                    if(bitV == 1){
                        //计算小数
                        right+=((float)(bitV)/(2L << movedForRightCount));
                    }
                    movedForRightCount++;
                }
            }
        }
        if(negative){
            left = -left;
            right = -right;
        }
        return left + right;
    }

    public static boolean quickJudgeAccurateFloat(String number) {
        if(StringUtil.isEmpty(number)) {
            return false;
        }
        return (number.indexOf('.') == -1) || ("0.5".equals(number) || ("0.25".equals(number))) || (number.endsWith("125") || number.endsWith("375") || number.endsWith("625") || number.endsWith("875"));
    }

    public static String floatRightToBytes(BigDecimal number, Integer length) {
        if(length == null || length > 32) {
            length = 32;
        }
        StringBuilder rightBuild = new StringBuilder(length);
        while (number.floatValue() > 0) {
            if (rightBuild.length() >= length) {
                if(number.floatValue() > 0) {
                    rightBuild.setCharAt(rightBuild.length()-1, '1');
                    break;
                }
            }
            BigDecimal temp = number.multiply(new BigDecimal("2"));
            if (temp.floatValue() >= 1) {
                rightBuild.append("1");
                number = temp.subtract(new BigDecimal("1"));
            } else {
                rightBuild.append("0");
                number = temp;
            }
        }
        return rightBuild.toString();
    }

    public static String floatToBytes(String numberStr) {
        if(StringUtil.isEmpty(numberStr)) {
            return null;
        }
        BigDecimal number = new BigDecimal(numberStr);
        char symbol;
        if(number.floatValue() < 0) {
            symbol = '1';
        } else {
            symbol = '0';
        }
        String[] elements = numberStr.split("\\.");
        BigDecimal left;
        BigDecimal right;
        if(elements.length < 2) {
            left = new BigDecimal(numberStr).abs();
            right = new BigDecimal("0");
        } else {
            left = new BigDecimal(elements[0]).abs();
            right = new BigDecimal("0."+elements[1]);
        }
        String leftStr = intToBinaryStringOriginal(left.intValue());
        String rightStr = floatRightToBytes(right, 23);

        StringBuilder exponentBuilder = new StringBuilder();
        exponentBuilder.append(leftStr);
        exponentBuilder.append(rightStr);

        int e = 0;
        while (exponentBuilder.length() > 0) {
            if('0' == exponentBuilder.charAt(0)) {
                exponentBuilder.deleteCharAt(0);
                e++;
            } else if('1' == exponentBuilder.charAt(0)) {
                exponentBuilder.deleteCharAt(0);
                e++;
                break;
            } else {
                throw new RuntimeException("what the fuck");
            }
        }
        if("0".equals(leftStr)) {
            //此处+1为减去leftStr多计算的小数点移动
            e = -e + 1;
        } else {
            e = leftStr.length() - 1;
        }
        e = e + 127;
        String eBitStr = intToBinaryStringOriginal(e);
        if(eBitStr.length() < 8) {
            StringBuilder eBitStrBuilder = new StringBuilder(eBitStr);
            int missingZero = 8 -eBitStr.length();
            for(int i=0; i<missingZero; i++) {
                eBitStrBuilder.insert(0, '0');
            }
            eBitStr = eBitStrBuilder.toString();
        }

        //符号位
        return symbol +
            //指数
            eBitStr +
            //底数
            exponentBuilder;
    }

    public static String toAzDecimal(int num) {
        String columnLetter = "";
        int mod;
        do {
            mod = (num % 26) + 1;
            columnLetter = (char) (64 + mod) + columnLetter;
            num = num / 26;
        } while (num > 0);
        return columnLetter;
    }

    private NumberUtil() {}
}
