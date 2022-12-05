package ru.com.m74.example.kotlin.service

import org.springframework.stereotype.Service
import ru.com.m74.example.kotlin.model.User
import ru.com.m74.example.kotlin.repository.UserRepository

@Service
class UserService(val repo: UserRepository) {

    fun findAll(): Iterable<User> = repo.findAll()

    fun save(user: User): User = repo.save(user)

    fun deleteById(id: Long) = repo.deleteById(id)
}