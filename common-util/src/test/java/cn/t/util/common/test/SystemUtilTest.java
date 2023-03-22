package cn.t.util.common.test;

import cn.t.util.common.SystemUtil;
import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;

public class SystemUtilTest {

    @Test
    public void getApplicationVarTest() {
        String key = "java.runtime.name";
        System.out.println(SystemUtil.getApplicationVar(key));
    }

    @Test
    public void getPidTest() {
        System.out.println(SystemUtil.getPid());
    }

    @Test
    public void getLocalIpv4Test() {
        System.out.println("public: " + SystemUtil.getLocalIpV4(false));
        System.out.println("private: " + SystemUtil.getLocalIpV4(true));
        System.out.println("public: " + Arrays.toString(SystemUtil.getLocalIpV4Bytes(false)));
        System.out.println("private: " + Arrays.toString(SystemUtil.getLocalIpV4Bytes(true)));
        System.out.println(StandardCharsets.UTF_8.name());
    }


    @Test
    public void printAllAddress() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> enumIpAdd = ni.getInetAddresses();
            while (enumIpAdd.hasMoreElements()) {
                InetAddress inetAddress = enumIpAdd.nextElement();
                System.out.println(inetAddress);
            }
        }
    }
}
