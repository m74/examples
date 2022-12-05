package ru.com.m74.example.kotlin.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import ru.com.m74.example.kotlin.model.User

interface UserRepository : CrudRepository<User, Long> {

    fun findAllByFullNameStartingWithIgnoreCase(name: String)

    @Query("select * from users where full_name like :query||'%'")
    fun findUserByName(query: String)
}