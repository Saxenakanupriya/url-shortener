package com.urlservice.urlshortener.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private ApplicationContext context;
    /**
     Method used to derive the short url using the original long url. More complex logic to derive

     @shorted url can be accomodated here.
     @return shortenedURL of length 50
     */
    public String shortenUrl(String originalUrl) {
        Jedis jedis = (Jedis) this.context.getBean("redisConnection");
        String shortenedUrl=jedis.get(originalUrl);
        if (shortenedUrl==null)
        {
            String randomString = RandomStringUtils.randomAlphanumeric(50).concat(originalUrl);
            shortenedUrl= RandomStringUtils.random(50, randomString);
            jedis.set(originalUrl,shortenedUrl);
            // Persist in DB after setting in the redis cache
        }
        return shortenedUrl;
    }


}
