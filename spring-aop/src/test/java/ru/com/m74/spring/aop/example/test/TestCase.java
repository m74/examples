package ru.com.m74.spring.aop.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.com.m74.spring.aop.example.Application;
import ru.com.m74.spring.aop.example.ExampleBean;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCase {

    @Resource
    private ExampleBean exampleBean;

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void helloTest() {
        assertEquals(exampleBean.sayHello(), "Hello Maxim!");
        log.debug("Done");
    }

    @Test
    public void testController() {
        assertEquals(restTemplate.getForEntity("/", String.class).getBody(), "Hello Maxim!");
    }
}
