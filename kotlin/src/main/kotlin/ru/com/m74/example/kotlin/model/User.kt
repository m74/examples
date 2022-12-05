package ru.com.m74.example.kotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * Пользователь
 */
@Table("users")
data class User(
    @Id
    val id: Long? = null,
    val fullName: String,
    val login: String,
    /**
     * пароль
     */
    @JsonIgnore
    val password: String? = null
) {
}