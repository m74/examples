package ru.com.m74.example.kotlin.mvc

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory.getLogger
import java.lang.Thread.sleep
import java.time.LocalTime
import kotlin.system.exitProcess

fun <T : Any> T.log(s: String, vararg args: Any) = getLogger(this.javaClass).info(s, *args)

val log = getLogger("audit")!!;

suspend fun hello(msg: String) {
    delay(1000)
    log.info(msg)
}

@DelicateCoroutinesApi
fun main() {

    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
//    sleep(2000)
//    exitProcess(0)

    runBlocking {
        val list: List<Deferred<Unit>> =
            (1..10).map { async { hello("1.$it") } }
        this.log("start: ${LocalTime.now()}")
        list.awaitAll()
        this.log("done")

        val jobs = (1..10).map { launch { hello("2.$it") } }
        this.log("start: {}", LocalTime.now())
//        jobs.joinAll();
    }
    log.info("done")
}