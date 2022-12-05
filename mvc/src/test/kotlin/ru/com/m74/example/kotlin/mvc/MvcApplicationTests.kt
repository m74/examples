package ru.com.m74.example.kotlin.mvc

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
class MvcApplicationTests() {

//    @Autowired
//    lateinit var webTestClient: WebTestClient;

    @Autowired
//    @Qualifier("testRestTemplate")
    lateinit var restTemplate: TestRestTemplate;

//    @Autowired
//    lateinit var webClient: WebClient;

    fun user(name: String, password: String? = null) =
        restTemplate.postForEntity("/", User(name = name, password = password), User::class.java)!!

    @Test
    fun helloTest() {

        user("Maxim")
        user("Ivan")
        user("Nikita")
        user("Ilya")
        user("Pavel")
        val users = restTemplate.getForEntity("/", Array<User>::class.java).body!!
//        webClient.get().uri("/")
//        val x =
//            webTestClient.get().uri("/").accept(APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody()
//        val users = x.collectList().block();
//        withContext(Dispatchers.IO) {
//        println(x.collectList().block())
//        }
        println("users: ${users.joinToString(", ")}")
    }

}
