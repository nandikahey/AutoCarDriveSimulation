package org.carsim.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for handling exceptions.
 */
public class ExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    private ExceptionHandler() {
    }

    public static void handleException(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
    }

}
