package cn.t.util.socket.messaging.convert;

public interface InstanceConvert {

    byte[] convertToBytes(Object obj);

    boolean support(Object obj);
}
