package cn.t.util.common;

public class ConvertUtil {

    public static long toIpNumber(String ipString) {
        byte[] ipBytes = toIpBytes(ipString);
        return ((ipBytes[0] & 0xFFL) << 24)|
            ((ipBytes[1]&0xFF) << 16)  |
            ((ipBytes[2]&0xFF) << 8)   |
            (ipBytes[3]&0xFF);
    }
    public static byte[] toIpBytes(int ip) {
        return new byte[] {
            (byte)((ip >>> 24) & ((byte)-1)),
            (byte)((ip >>> 16) & ((byte)-1)),
            (byte)((ip >>> 8) & ((byte)-1)),
            (byte)(ip & ((byte)-1)),
        };
    }
    public static byte[] toIpBytes(String ipString) throws RuntimeException {
        String[] octets = ipString.split("\\.");
        if(octets.length != 4) {
            throw new IllegalArgumentException("Invalid IP address: " + ipString);
        }
        byte[] addressBytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            int octetValue = Integer.parseInt(octets[i]);
            if (octetValue < 0 || octetValue > 255) {
                throw new IllegalArgumentException("Invalid IP address: " + ipString);
            }
            addressBytes[i] = (byte) octetValue;
        }
        return addressBytes;
    }
    public static String toIpString(int ipNum) {
        byte[] ipBytes = toIpBytes(ipNum);
        StringBuilder builder = new StringBuilder(String.valueOf(ipBytes[0] & 0xFF));
        for (int i = 1; i < ipBytes.length; i++) {
            builder.append(".").append(ipBytes[i] & 0xFF);
        }
        return builder.toString();
    }
}
