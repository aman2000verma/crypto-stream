package com.app.utils;

import java.util.logging.Logger;

public class LogUtil {
    private final static Logger log = Logger.getLogger(LogUtil.class.getName());

    public static void console(String data) {
        log.info(data + "\n");
    }

    public static void error(String data) {
        log.severe(data + "\n");
    }

    public static void warn(String data) {
        log.warning(data + "\n");
    }
}