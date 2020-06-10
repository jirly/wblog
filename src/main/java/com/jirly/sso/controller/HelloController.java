package com.jirly.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2020/6/9
 */
@RestController
public class HelloController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/getUser")
    @Cacheable(value="user-key",key="1",cacheManager = "redisCacheManager")
    public Map<String,Object> getUser() {
        Map<String,Object> user = new HashMap<>();
        user.put("name","小明2");
        user.put("secret","xxxx");
        return user;
    }

    @RequestMapping("/test")
    @CachePut(value = "user-key",key="1")
    public Map<String,Object> test() {
        Map<String,Object> user = new HashMap<>();
        user.put("name","小明");
        user.put("secret","xxxx");
        redisTemplate.opsForValue().set("test",user);
        Map<String,Object> test = (Map<String, Object>) redisTemplate.opsForValue().get("test");
        System.out.println(test.get("name"));
        return user;
    }
}
