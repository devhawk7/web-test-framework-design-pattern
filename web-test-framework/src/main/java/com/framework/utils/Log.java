package com.framework.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Thin wrapper over Log4j2 adding a custom ACTION level (between INFO=400 and DEBUG=500). */
public final class Log {
    public static final Level ACTION = Level.forName("ACTION", 350);
    private final Logger logger;

    private Log(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    public static Log forClass(Class<?> clazz) {
        return new Log(clazz);
    }

    public void debug(String msg, Object... args)  { logger.debug(msg, args); }
    public void info(String msg, Object... args)   { logger.info(msg, args); }
    /** User-like action performed on the page (click, type, open ...). */
    public void action(String msg, Object... args) { logger.log(ACTION, msg, args); }
    public void warn(String msg, Object... args)   { logger.warn(msg, args); }
    public void error(String msg, Object... args)  { logger.error(msg, args); }
    public void error(String msg, Throwable t)     { logger.error(msg, t); }
}
