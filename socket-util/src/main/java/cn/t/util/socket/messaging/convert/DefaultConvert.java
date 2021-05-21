package cn.t.util.socket.messaging.convert;

public class DefaultConvert implements InstanceConvert {

    @Override
    public byte[] convertToBytes(Object obj) {
        if (obj == null) {
            return new byte[0];
        }
        return obj.toString().getBytes();
    }

    @Override
    public boolean support(Object obj) {
        return true;
    }
}
