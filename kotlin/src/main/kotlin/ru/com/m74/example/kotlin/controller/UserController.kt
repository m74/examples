package ru.com.m74.example.kotlin.controller

import org.springframework.web.bind.annotation.*
import ru.com.m74.example.kotlin.model.User
import ru.com.m74.example.kotlin.service.UserService

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {

    @GetMapping
    fun index() = service.findAll()

    @PostMapping
    fun insert(@RequestBody user: User): User = service.save(user)

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User): User {
        return service.save(user.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.deleteById(id)
}