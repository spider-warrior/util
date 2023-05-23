package cn.t.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryUtil {

    private static final Logger logger = LoggerFactory.getLogger(RetryUtil.class);

    public static void executeTask(Runnable runnable, int tryTimes, String taskName, String extraInfo) {
        int tryTime = 0;
        while (true) {
            try {
                runnable.run();
                break;
            } catch (Exception e) {
                if(++tryTime > tryTimes) {
                    throw e;
                } else {
                    logger.warn("[{}]失败, exception: {} , 重试: {}, extraInfo: {}", taskName, e.getClass().getSimpleName(), tryTime, extraInfo);
                }
            }
        }
    }

    public static <T> T executeTask(Callable<T> callable, int tryTimes, String taskName, String extraInfo) {
        int tryTime = 0;
        while (true) {
            try {
                return callable.call();
            } catch (Exception e) {
                if(++tryTime > tryTimes) {
                    throw new RuntimeException(e);
                } else {
                    logger.warn("[{}]失败, exception: {} , 重试: {}, extraInfo: {}", taskName, e.getClass().getSimpleName(), tryTime, extraInfo);
                }
            }
        }
    }
}
