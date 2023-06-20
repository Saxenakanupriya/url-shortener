package com.urlservice.urlshortener.controller;

import com.urlservice.urlshortener.pojo.UrlRequestObj;
import com.urlservice.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/api/")
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "/shorten-url")
    public String shortenUrl(@RequestParam String originalUrl) {
        String shortenedUrl= urlService.shortenUrl(originalUrl);
        return shortenedUrl;
    }
}
