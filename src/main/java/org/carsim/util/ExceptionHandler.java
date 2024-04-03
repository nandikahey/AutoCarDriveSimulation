package org.carsim.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    private ExceptionHandler() {
    }

    public static void handleException(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
    }

}
