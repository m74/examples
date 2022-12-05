package ru.com.m74.mtls.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @GetMapping("/")
    fun index() = "Simple SSL mTLS resource"
}