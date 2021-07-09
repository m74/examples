package ru.com.m74.examples.aspectj.test;

/**
 * @author mixam
 * @since 10.01.18 22:21
 */
public class AspectTestObject {
    public boolean test() {
        System.out.println("original value: " + false);
        return false;
    }
}
