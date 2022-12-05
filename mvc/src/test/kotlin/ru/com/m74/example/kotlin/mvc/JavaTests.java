package ru.com.m74.example.kotlin.mvc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class JavaTests {

    private final Logger logger = LoggerFactory.getLogger("test");

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void heloTest() {
        User[] users = testRestTemplate.getForEntity("/", User[].class).getBody();
        assert users != null;
        logger.info("{}", Arrays.stream(users).map(User::toString).collect(Collectors.joining()));
    }
}
