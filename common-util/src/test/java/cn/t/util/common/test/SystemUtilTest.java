package cn.t.util.common.test;

import cn.t.util.common.SystemUtil;
import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class SystemUtilTest {

    @Test
    public void getApplicationVarTest() {
        String key = "java.runtime.name";
//        String key = "abc";
        System.out.println(SystemUtil.getApplicationVar(key));
    }

    @Test
    public void getPidTest() {
        System.out.println(SystemUtil.getPid());
    }

    @Test
    public void getLocalIpv4Test() {
        System.out.println("private: " + SystemUtil.getLocalIpV4(true));
        System.out.println("public: " + SystemUtil.getLocalIpV4(false));
    }

    @Test
    public void getLocalIpv6Test() {
        System.out.println("private: " + SystemUtil.getLocalIpV6(true));
        System.out.println("public: " + SystemUtil.getLocalIpV6(false));
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

    @Test
    public void nowSerialTest() {
        long startTime = System.currentTimeMillis();
        for(int i=0; i<100000000; i++) {
            System.currentTimeMillis();
        }
        System.out.println(String.format("%d mills", (System.currentTimeMillis() - startTime)));
        startTime = System.currentTimeMillis();
        for(int i=0; i<100000000; i++) {
            SystemUtil.now();
        }
        System.out.println(String.format("%d mills", (System.currentTimeMillis() - startTime)));
    }

    @Test
    public void nowParallelTest() throws Exception {
        int parallelCount = 10;
        final CountDownLatch countDownLatch1 = new CountDownLatch(parallelCount);
        long startTime = System.currentTimeMillis();
        for(int i=0; i<20; i++) {
            new Thread(() -> {
                for(int j = 0; j <100000000; j++) {
                    System.currentTimeMillis();
                }
                countDownLatch1.countDown();
            }).start();
        }
        countDownLatch1.await();
        System.out.println(String.format("%d mills", (System.currentTimeMillis() - startTime)));

        CountDownLatch countDownLatch2 = new CountDownLatch(parallelCount);
        startTime = System.currentTimeMillis();
        for(int i=0; i<20; i++) {
            new Thread(() -> {
                for(int j = 0; j <100000000; j++) {
                    SystemUtil.now();
                }
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
        System.out.println(String.format("%d mills", (System.currentTimeMillis() - startTime)));
    }
}
