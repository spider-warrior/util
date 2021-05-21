package cn.t.util.common.digital;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public final class ByteSequence extends DataInputStream {

    private final ByteArrayStream byteStream;

    public ByteSequence(final byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byteStream = (ByteArrayStream) in;
    }

    public final int getIndex() {
        return byteStream.getPosition();
    }


    final void unreadByte() {
        byteStream.unreadByte();
    }

    private static final class ByteArrayStream extends ByteArrayInputStream {

        ByteArrayStream(final byte[] bytes) {
            super(bytes);
        }

        final int getPosition() {
            return pos;
        }

        final void unreadByte() {
            if (pos > 0) {
                pos--;
            }
        }
    }
}
