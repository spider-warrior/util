package cn.t.util.common.nio;

import java.nio.ByteBuffer;

public class ByteBufferUtil {

    public static ByteBuffer newCapacity(ByteBuffer byteBuffer, int add) {
        ByteBuffer newBuffer = ByteBuffer.allocateDirect(byteBuffer.capacity() + add);
        return copyBuffer(byteBuffer, newBuffer);
    }

    public static ByteBuffer newCapacity(ByteBuffer byteBuffer) {
        ByteBuffer newBuffer = ByteBuffer.allocateDirect(byteBuffer.capacity() * 2);
        return copyBuffer(byteBuffer, newBuffer);
    }

    private static ByteBuffer copyBuffer(ByteBuffer oldByteBuffer, ByteBuffer newByteBuffer) {
        oldByteBuffer.flip();
        return newByteBuffer.put(oldByteBuffer);
    }
}
