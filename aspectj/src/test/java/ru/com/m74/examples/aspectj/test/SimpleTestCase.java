package ru.com.m74.examples.aspectj.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SimpleTestCase {

    @Test
    public void doAspectTest() {
        SimpleObject simpleObject = new SimpleObject();
        boolean result = simpleObject.test();
        log.info("result: " + result);
        assert (result);

    }


}