package com.sompopo.api.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.annotation.EnableCaching;

@Configuration
//@EnableCaching 
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("users"); // Cache name
        Caffeine caffeineBuilder = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(200)
                .expireAfterAccess(2, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();

        cacheManager.setCaffeine(caffeineBuilder);
        return cacheManager;
    }
}