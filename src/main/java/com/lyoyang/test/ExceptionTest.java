package com.lyoyang.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);


    @Test
    public void testException() {

        try {
            int a = 1/0;
        } catch (Exception e) {
//            LOGGER.error("发生异常", e);
            throw e;
        } finally {
            LOGGER.info("close resources...");
        }

    }


}
