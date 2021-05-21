package cn.t.util.common;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class LoggerUtil {

    public static void setJdkRootLoggerLevel(Level level) {
        LogManager.getLogManager().getLogger("").setLevel(level);
    }

    public static void setSlf4jRootLoggerLevel(org.slf4j.event.Level level) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) loggerFactory;
            loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).setLevel(ch.qos.logback.classic.Level.toLevel(level.toString()));
        }
    }

    public static void setSlf4jLoggerLevel(String key, org.slf4j.event.Level level) {
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) loggerFactory;
            loggerContext.getLogger(key).setLevel(ch.qos.logback.classic.Level.toLevel(level.toString()));
        }
    }
}
