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
    private static final String LOCAL_PRIVATE_IP_6 =  getLocalIpV6(true);

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

    public static String getCashedLocalPrivateIpV4() {
        return LOCAL_PRIVATE_IP_4;
    }

    public static String getLocalIpV6(boolean isPrivate) {
        return getLocalIp(Inet6Address.class, isPrivate);
    }

    public static String getCashedLocalPrivateIpV6() {
        return LOCAL_PRIVATE_IP_6;
    }

    private static String getLocalIp(Class<? extends InetAddress> clazz, boolean isPrivate) {
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
                    if (!inetAddress.isLinkLocalAddress()
                        && (isPrivate == inetAddress.isSiteLocalAddress())
                        && clazz.isAssignableFrom(inetAddress.getClass())
                    ) {
                        networkInterfaceStack.push(inetAddress);
                    }
                }
            }
            InetAddress inetAddress = networkInterfaceStack.peek();
            if(inetAddress == null) {
                return "";
            }
            return inetAddress.getHostAddress();
        } catch (SocketException e) {
           throw new RuntimeException(e);
        }
    }

    public static byte[] convertHostToBytes(String host) throws UnknownHostException {
        return InetAddress.getByName(host).getAddress();
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
