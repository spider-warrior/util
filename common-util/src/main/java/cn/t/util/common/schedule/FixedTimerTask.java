package cn.t.util.common.schedule;

import java.util.TimerTask;

public class FixedTimerTask extends TimerTask {

    private final Runnable runnable;

    public FixedTimerTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
