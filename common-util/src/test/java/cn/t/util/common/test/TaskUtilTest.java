package cn.t.util.common.test;

import cn.t.util.common.schedule.TaskUtil;
import org.junit.Test;

public class TaskUtilTest {

    @Test
    public void simpleTaskTest() {
        int second = 200;
        class Echo implements Runnable {
            @Override
            public void run() {
                System.out.println("time: " + System.currentTimeMillis());
            }
        }
        TaskUtil.createFixedTimerTask(second, 0, new Echo());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
