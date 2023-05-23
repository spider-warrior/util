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
            } catch (Throwable t) {
                if(++tryTime > tryTimes) {
                    throw t;
                } else {
                    Throwable causedBy = ExceptionUtil.getCausedBy(t);
                    logger.warn("[{}]失败, exceptionClass: {}, exceptionMessage: {}, 重试: {}, extraInfo: {}", taskName, causedBy.getClass().getSimpleName(), causedBy.getMessage(), tryTime, extraInfo);
                }
            }
        }
    }

    public static <T> T executeTask(Callable<T> callable, int tryTimes, String taskName, String extraInfo) {
        int tryTime = 0;
        while (true) {
            try {
                return callable.call();
            } catch (Throwable t) {
                if(++tryTime > tryTimes) {
                    throw new RuntimeException(t);
                } else {
                    Throwable causedBy = ExceptionUtil.getCausedBy(t);
                    logger.warn("[{}]失败, exceptionClass: {}, exceptionMessage: {}, 重试: {}, extraInfo: {}", taskName, causedBy.getClass().getSimpleName(), causedBy.getMessage(), tryTime, extraInfo);
                }
            }
        }
    }
}
