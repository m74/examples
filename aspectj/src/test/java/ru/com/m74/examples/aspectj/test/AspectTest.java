package ru.com.m74.examples.aspectj.test;

import org.junit.Test;

public class AspectTest {

    @Test
    public void doAspectTest() {
        AspectTestObject aspectTestObject = new AspectTestObject();
        boolean result = aspectTestObject.test();
        System.out.println("result: " + result);
        assert (result);

    }


}