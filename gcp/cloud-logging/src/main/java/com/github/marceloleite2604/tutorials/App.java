package com.github.marceloleite2604.tutorials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Logging INFO with Logback");
        logger.error("Logging ERROR with Logback");
    }

}
