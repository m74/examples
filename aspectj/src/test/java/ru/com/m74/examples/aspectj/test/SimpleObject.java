package ru.com.m74.examples.aspectj.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mixam
 * @since 10.01.18 22:21
 */
@Slf4j
public class SimpleObject {
    public boolean test() {
        log.info("original value: {}", false);
        return false;
    }
}
