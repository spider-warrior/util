package cn.t.util.common.test;

import cn.t.util.common.nio.ByteBufferUtil;
import org.junit.Test;

import java.nio.ByteBuffer;

public class ByteBufferUtilTest {

    @Test
    public void newCapacityTest() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);
        byteBuffer.putInt(1);
        byteBuffer = ByteBufferUtil.newCapacity(byteBuffer);
        byteBuffer.putInt(2);
        byteBuffer = ByteBufferUtil.newCapacity(byteBuffer);
        byteBuffer.putInt(3);
        byteBuffer.putInt(4);

        byteBuffer.flip();
        while (byteBuffer.remaining() > 0) {
            System.out.println(byteBuffer.getInt());
        }
    }

    /**
     * netty 使用demo
     * */
    @Test
    public void byteArrayToByteBufTest() {
//        byte[] bytes = {1,2,3,4,5,6,7};
//        Unpooled.wrappedBuffer(bytes);
    }

}
