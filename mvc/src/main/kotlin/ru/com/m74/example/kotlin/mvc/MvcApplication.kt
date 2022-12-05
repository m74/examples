package ru.com.m74.example.kotlin.mvc

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.benmanes.caffeine.cache.Cache
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MvcApplication

fun main(args: Array<String>) {
    runApplication<MvcApplication>(*args)
}

@RestController
@RequestMapping("/")
class DefaultController(private val repo: UserRepository, val service: UserService) {

    val log = LoggerFactory.getLogger(javaClass)!!;

    val all = repo.findAll().flatMap({
        println("before: $it")
        repo.findById(it.id!!).log().doOnNext {
            println("after: $it")
        }
    }, 100)
//        .parallel().runOn(Schedulers.immediate()).sequential()

//        .collect().cache(Duration.ofMinutes(3))

    //    @Cacheable("allUsers")
    @GetMapping
    fun index(): List<User>? {
        log.info("findAll")
        return all.collectList().block()
    }

    @PostMapping
    fun save(@RequestBody user: User) = repo.save(user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repo.deleteById(id)

    @GetMapping("/{id}")
    fun findUser(@PathVariable id: Long): User {
        return service.getUserSync(id)
//        return service.getUserUsingCacheable(id)
    }
}

@Table("users")
data class User(
    @Id
    val id: Long? = null,
    val name: String,
    @JsonIgnore
    val password: String?
)

interface UserRepository : ReactiveCrudRepository<User, Long>;

@Service
class UserService(val repo: UserRepository, val cache: Cache<Long, User>) {

    fun getUserUsingCache(id: Long): User? {
        val user: User? = cache.get(id) {
            runBlocking {
                log.info("findUser: $it")
//            repo.findById(it).block()
                getUser(it)
            }
        }
        return user;
    }

    @Cacheable("users", key = "#id")
    fun getUserUsingCacheable(id: Long): User {
        return runBlocking {
            getUser(id)
        }
//        return user;
    }


    suspend fun getUser(id: Long): User {
        log.info("findUser: $id")
        delay(1000)
        return repo.findById(id).awaitSingle()
//        return User(id = id, name = "test", password = "")
    }

    @Cacheable("labels", key = "#id")
    fun getUserSync(id: Long): User = runBlocking {
        log.info("findUser: $id")
        delay(1000)
        repo.findById(id).awaitSingle()
//        return User(id = id, name = "test", password = "")
    }
}
