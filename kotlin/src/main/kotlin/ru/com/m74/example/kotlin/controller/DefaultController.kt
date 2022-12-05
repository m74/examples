package ru.com.m74.example.kotlin.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/")
class DefaultController() {

    val log = LoggerFactory.getLogger(javaClass)

    interface Message {
        fun getExampe() = "Example"
    }

    @GetMapping
    fun index() = object : Message {
        val text
            get() = "Hello world!!!"
        val now = LocalDateTime.now()

        init {
            log.info("init message: {}", this)
        }
    }

}