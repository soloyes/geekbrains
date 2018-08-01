package xyz.solovev.enterprise.utils;

import xyz.solovev.enterprise.servlet.MainServlet;

import java.util.logging.Logger;

public class LogSystem {
    private static final Logger logger = java.util.logging.Logger.getLogger(MainServlet.class.getSimpleName());

    public static Logger getLogger() {
        return logger;
    }
}
