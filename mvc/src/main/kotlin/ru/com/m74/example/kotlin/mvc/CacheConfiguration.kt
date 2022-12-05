package ru.com.m74.example.kotlin.mvc

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


@EnableCaching
@Configuration
class CacheConfiguration(
    @Value("\${cache.maximumSize}") val maximumSize: Long = 1000,
    @Value("\${cache.expireAfterWrite}") val expireAfterWrite: Long = 3,
) {

//    @Bean
//    fun asyncCacheManager(): CacheManager {
//        val cache: AsyncLoadingCache<String, User> = Caffeine.newBuilder()
//            .maximumSize(100)
//            .expireAfterWrite(1, TimeUnit.MINUTES)
//            .buildAsync { k -> k
////                User(name = "", password = "")
//            }
//        val caffeineCacheManager = CaffeineCacheManager()
//        caffeineCacheManager.setCaffeine(cache)
//        return caffeineCacheManager
//    }

    @Bean
    fun cacheManager(): CacheManager {
        val cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)

        val caffeineCacheManager = CaffeineCacheManager()
        caffeineCacheManager.setCaffeine(cache)
        return caffeineCacheManager
    }

    @Bean
    fun cache(): Cache<Long, User> = Caffeine.newBuilder()
        .maximumSize(maximumSize)
        .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES).build()
}
