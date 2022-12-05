package ru.com.m74.example.kotlin.mvc

import kotlinx.coroutines.runBlocking
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Flux

val client: WebClient = WebClient.create("http://localhost:8080");

//suspend fun getUsers(): Deferred<List<User>> {
//    val job: Deferred<List<User>> = coroutineScope {
//        async {
//            client.get().uri("/").accept(APPLICATION_JSON).retrieve().awaitBody();
//        }
//    }
//
//    return job;
//}


fun retrieveUsers(): WebClient.ResponseSpec {
    return client.get().uri("/").accept(APPLICATION_JSON).retrieve();
}

suspend fun getUsers(): List<User> = retrieveUsers().awaitBody()

//    println("users: $users");
//    return users

fun main() {
    val users: Flux<User> = retrieveUsers().bodyToFlux(User::class.java);
    println("flux: ${users.collectList().block()}")

    runBlocking {
        println("coroutines: ${getUsers()}")
    }
}