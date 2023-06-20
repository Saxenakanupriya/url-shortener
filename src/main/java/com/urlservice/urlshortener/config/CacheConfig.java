package com.urlservice.urlshortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class CacheConfig {
    @Autowired
    private ApplicationContext context;

    @Bean("redisConnection")
    public Jedis getRedisConnection()
    {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }


    /**
     * Initial setup for cache. In productive scenerio, when the application starts up, the cache can be loaded from the DB.
     */
    @Bean("initializeRedis")
    public void initializeRedis()
    {
        Jedis jedis = (Jedis) this.context.getBean("redisConnection");
        jedis.set("long-url-randomString123456","short-url-1");
        jedis.set("long-url-randomString789101","short-url-2");
        jedis.set("long-url-randomString121314","short-url-3");
        jedis.set("long-url-randomString161718","short-url-4");
        jedis.set("long-url-randomString192021","short-url-5");
    }
}