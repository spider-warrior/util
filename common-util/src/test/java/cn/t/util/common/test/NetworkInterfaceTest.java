package cn.t.util.common.test;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkInterfaceTest {
    public static void main(String[] args) throws Exception {

        InetAddress ipAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
        byte[] mac = networkInterface.getHardwareAddress();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        System.out.println(ipAddress);
        System.out.println(builder);
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

        try(DatagramSocket s=new DatagramSocket())
        {
            s.connect(InetAddress.getByAddress(new byte[]{1,1,1,1}), 0);
            mac = NetworkInterface.getByInetAddress(s.getLocalAddress()).getHardwareAddress();
            builder = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(builder);
        }

//        List<NetworkInterface> networkInterfaceList = new ArrayList<>();
//        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//        while (interfaces.hasMoreElements()) {
//            NetworkInterface ni = interfaces.nextElement();
//            if(ni.isLoopback() || ni.isVirtual() || !ni.isUp()) {
//                continue;
//            } else {
//                Enumeration<InetAddress> enumIpAdd = ni.getInetAddresses();
//                while (enumIpAdd.hasMoreElements()) {
//                    InetAddress inetAddress = enumIpAdd.nextElement();
//                    if(inetAddress.isSiteLocalAddress()) {
//                        byte[] mac = ni.getHardwareAddress();
//                        StringBuilder builder = new StringBuilder();
//                        for (int i = 0; i < mac.length; i++) {
//                            builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//                        }
//                        System.out.println(inetAddress);
//                        System.out.println(builder);
//                    }
//                }
//                ni.getInetAddresses();
//                networkInterfaceList.add(ni);
//            }
//        }
//        for (NetworkInterface networkInterface : networkInterfaceList) {
//            System.out.println(networkInterface);
//        }
    }
}
