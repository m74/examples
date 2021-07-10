package ru.com.m74.examples.aspectj.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.com.m74.examples.aspectj.Application;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringTestCase {

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void aspectTest() {
        assertEquals(restTemplate.getForEntity("/hello", String.class).getBody(), "Hello Maxim!");
    }
}
