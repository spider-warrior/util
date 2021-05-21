package cn.t.util.common.test;

import cn.t.util.common.LoggerUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class LoggerUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtilTest.class);

    @Test
    public void setSlf4jLoggerLevelTest() {
        LoggerUtil.setSlf4jRootLoggerLevel(Level.INFO);
    }

    @Test
    public void testSetSlf4jRootLoggerLevel() {
        LoggerUtil.setSlf4jRootLoggerLevel(Level.WARN);
        logger.trace("0");
        logger.debug("1");
        logger.info("2");
        logger.warn("3");
        logger.error("4");
    }

    @Test
    public void testSetSlf4jLoggerLevel() {
        String key = "cn.t.util.common";
        LoggerUtil.setSlf4jLoggerLevel(key, Level.ERROR);
        logger.trace("0");
        logger.debug("1");
        logger.info("2");
        logger.warn("3");
        logger.error("4");
    }

}
