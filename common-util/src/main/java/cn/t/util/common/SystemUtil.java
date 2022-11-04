package cn.t.util.common;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemUtil {

    private static final String LOCAL_PRIVATE_IP_4 =  getLocalIpV4(true);
//    private static final String LOCAL_PRIVATE_IP_6 =  getLocalIpV6(true);

    public static String getApplicationVar(String key) {
        return Optional.ofNullable(SystemUtil.getSysProperty(key)).orElseGet(() -> SystemUtil.getSysEnv(key));
    }

    public static String getSysEnv(String key) {
        return System.getenv(key);
    }

    public static String getSysProperty(String key) {
        return System.getProperty(key);
    }

    public static String getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        return name.substring(0, name.indexOf('@'));
    }

    public static String getLocalIpV4(boolean isPrivate) {
        return getLocalIp(Inet4Address.class, isPrivate);
    }

    public static byte[] getLocalIpV4Bytes(boolean isPrivate) {
        return getLocalIpBytes(Inet4Address.class, isPrivate);
    }

    public static String getCashedLocalPrivateIpV4() {
        return LOCAL_PRIVATE_IP_4;
    }

//    public static String getLocalIpV6(boolean isPrivate) {
//        return getLocalIp(Inet6Address.class, isPrivate);
//    }

//    public static byte[] getLocalIpV6Bytes(boolean isPrivate) {
//        return getLocalIpBytes(Inet6Address.class, isPrivate);
//    }

//    public static String getCashedLocalPrivateIpV6() {
//        return LOCAL_PRIVATE_IP_6;
//    }

    private static byte[] getLocalIpBytes(Class<? extends InetAddress> clazz, boolean isPrivate) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            Deque<InetAddress> networkInterfaceStack = new LinkedList<>();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if(ni.isLoopback() || !ni.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> enumIpAdd = ni.getInetAddresses();
                while (enumIpAdd.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAdd.nextElement();
                    if ((isPrivate == inetAddress.isSiteLocalAddress())
                        && clazz.isAssignableFrom(inetAddress.getClass())
                    ) {
                        networkInterfaceStack.push(inetAddress);
                    }
                }
            }
            InetAddress inetAddress = networkInterfaceStack.peek();
            if(inetAddress == null) {
                return null;
            } else {
                return inetAddress.getAddress();
            }
        } catch (SocketException e) {
           throw new RuntimeException(e);
        }
    }

    private static String getLocalIp(Class<? extends InetAddress> clazz, boolean isPrivate) {
        byte[] bytes = getLocalIpBytes(clazz, isPrivate);
        if(bytes == null || bytes.length == 0) {
            return null;
        } else {
            return (bytes[0] & 0xff) + "." + (bytes[1] & 0xff) + "." + (bytes[2] & 0xff) + "." + (bytes[3] & 0xff);
        }
//        if(bytes == null || bytes.length == 0) {
//            return null;
//        } else {
//            if(bytes.length == 4) {
//                return (bytes[0] & 0xff) + "." + (bytes[1] & 0xff) + "." + (bytes[2] & 0xff) + "." + (bytes[3] & 0xff);
//            } else if(bytes.length == 16) {
//                StringBuilder sb = new StringBuilder(39);
//                for (int i = 0; i < (16 / 2); i++) {
//                    sb.append(Integer.toHexString(((bytes[i<<1]<<8) & 0xff00)
//                        | (bytes[(i<<1)+1] & 0xff)));
//                    if (i < (16 / 2) -1 ) {
//                        sb.append(":");
//                    }
//                }
//                return sb.toString();
//            } else {
//                return null;
//            }
//        }
    }

    public static byte[] convertHostToBytes(String host) throws UnknownHostException {
        return InetAddress.getByName(host).getAddress();
    }

    public static boolean isSiteLocalAddress(byte[] ipParts) {
        if(ipParts.length != 4) {
            return false;
        }
        int ipParts0 = ipParts[0] & 0xFF;
        return ipParts0 == 10
            || ipParts0 == 172 && ipParts[1] == 16
            || ipParts0 == 192 && (ipParts[1] & 0xFF) == 168;
    }

    public static void showMtu() throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface ni = en.nextElement();
            System.out.println(" Display Name = " + ni.getDisplayName());
            System.out.println(" MTU = " + ni.getMTU());
        }
    }

    public static long now() {
        return SystemClock.now;
    }

    private static final class SystemClock {
        private static volatile long now = System.currentTimeMillis();
        static {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
                Thread thread = new Thread(runnable, "system-clock-optimize");
                thread.setDaemon(true);
                return thread;
            });
            scheduler.scheduleAtFixedRate(() -> now = System.currentTimeMillis(), 5, 5, TimeUnit.MILLISECONDS);
        }
    }

}
