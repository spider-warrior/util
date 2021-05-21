package cn.t.util.common.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author yj
 * @since 2019-12-12 10:52
 *
 * 参考地址: https://www.cnblogs.com/zongmin/p/11569760.html
 *
 * //a = a & b
 * void and(BitSet set);
 *
 * //a = a & !b
 * void andNot(BitSet set);
 *
 * //a = a^b
 * void xor(BitSet set);
 *
 * //a = a | b
 * void or(BitSet set);
 *
 * //将指定索引处的位设置为 true
 * void set(int bitIndex)
 *
 * //将指定索引处的位设置为指定的值
 * void set(int bitIndex, boolean value);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为 true
 * void set(int fromIndex, int toIndex);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为指定的值
 * void set(int fromIndex, int toIndex, boolean value);
 *
 * //返回指定索引处的位值
 * boolean get(int bitIndex);
 *
 * //返回一个新的 BitSet，它由 fromIndex（包括）到 toIndex（不包括）范围内的位组成
 * BitSet get(int fromIndex, int toIndex);
 *
 * //返回此 BitSet 的“逻辑大小”，即实际使用的位数
 * int length();
 *
 * //返回此 BitSet 表示位值时实际使用空间的位数，即 words.length * 64
 * int size();
 *
 * //将此 BitSet 中的所有位设置为 false
 * void clear();
 *
 * //将索引指定处的位设置为 false
 * void clear(int bitIndex);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为 false
 * void clear(int fromIndex, int toIndex);
 *
 * //将指定索引处的值设置为其当前值的补码
 * void flip(int bitIndex);
 *
 * //将 fromIndex（包括）到指定的 toIndex（不包括）范围内的每个位设置为其当前值的补码
 * void flip(int fromIndex, int toIndex);
 *
 * //返回此 BitSet 中设置为 true 的位数
 * int cardinality();
 *
 * //如果指定 BitSet 中设置为 true 的位，在此 BitSet 中也为 true，则返回 ture
 * boolean intersects(BitSet set);
 *
 * //如果此 BitSet 中没有包含任何设置为 true 的位，则返回 ture
 * boolean isEmpty();
 *
 * //返回 fromIndex（包括）之后第一个设置为 false 的位的索引
 * int nextClearBit(int fromIndex);
 *
 * //返回 fromIndex（包括）之后的第一个设置为 true 的位的索引
 * int nextSetBit(int fromIndex);
 *
 * //返回该 BitSet 中为 true 的索引的字符串拼接形式
 * String toString();
 *
 * //返回 hashcode 值
 * int hashcode();
 *
 * //复制此 BitSet，生成一个与之相等的新 BitSet。
 * Object clone();
 *
 * //将此对象与指定的对象进行比较。
 * boolean equals(Object obj);
 *
 * //返回该bit set中设置为true的数量
 * int cardinality();
 **/
public class BitSetTest {

    @Test
    public void usageTest() {
        BitSet bitSet = new BitSet();
        for (int i=0; i<32; i+=2) {
            bitSet.set(i);
        }
        //此处使用bitSet.size()会输出64个元素(底层实现为long数组)
        //使用length()是真实的操作数量
        for(int i=0; i<bitSet.length(); i++) {
            System.out.println(String.format("index: %d, value: %b", i, bitSet.get(i)));
        }
    }

    @Test
    public void commonTest() {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) {
                bits1.set(i);    //1010-1010-1010-1010
            }

            if ((i % 5) != 0) {  //0111-1011-1101-1110
                bits2.set(i);
            }
        }

        System.out.println(bits1);  //{0, 2, 4, 6, 8, 10, 12, 14}
        System.out.println(bits2);  //{1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}

        // bits2 = bits2 & bits1
        bits2.and(bits1);           //0010-1010-1000-1010
        System.out.println(bits2);  //{2, 4, 6, 8, 12, 14}

        // bits2 = bits2 | bits1
        bits2.or(bits1);            //1010-1010-1010-1010
        System.out.println(bits2);  //{0, 2, 4, 6, 8, 10, 12, 14}

        // bits2 = bits2 ^ bits1
        bits2.xor(bits1);            //0000-0000-0000-0000
        System.out.println(bits2);   //{}
    }


    /**
     * 有 1 千万个随机数，随机数的范围在 1 到 1 亿之间。现在要求写出一种算法，将 1 到 1 亿之间没有在随机数中出现的数求出来？
     * */
    public static void main(String args[]) {
        Random random = new Random();
        //索引为 0 的位置弃用
        BitSet bitSet = new BitSet(100000001);

        for (int i = 0; i < 10000000; i++) {
            //随机选取 1 到 100000000 的数，并将 BitSet 中相应的位设为 true
            bitSet.set(random.nextInt(100000000) + 1);
        }

        System.out.println("1~1 亿不在上述随机数中有：" + (100000000 - bitSet.cardinality()));
        for(int i = 1; i <= 100000000; i++) {
            if(!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void bitSetPersistTest() {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(3);
        bitSet.set(5);
        byte[] data = bitSet.toByteArray();
        System.out.println("bytes: " + Arrays.toString(data));



    }

}
