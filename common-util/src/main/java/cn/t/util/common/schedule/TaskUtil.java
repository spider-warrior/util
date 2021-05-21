package cn.t.util.common.schedule;

import java.util.Timer;

public class TaskUtil {

    public static Timer createFixedTimerTask(int period, int delay, Runnable runnable) {
        Timer timer = new Timer();
        timer.schedule(new FixedTimerTask(runnable), delay, period);
        return timer;
    }

}

