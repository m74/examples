package ru.com.m74.example.kotlin

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.test.context.TestPropertySource
import ru.com.m74.example.kotlin.model.User

@TestPropertySource("classpath:test.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@Test
	fun `Simple CRUD Test`() {
		val url = "/users"
		val u = restTemplate.postForEntity<User>(url, User(login = "user", fullName = "none")).body
		restTemplate.postForEntity<User>(url, User(login = "maxim", fullName = "Maxim Smirnov"))
		restTemplate.postForEntity<User>(url, u?.copy(fullName = "User"))
		val resp = restTemplate.getForEntity<List<User>>(url)
		println(resp.body)
	}
}
