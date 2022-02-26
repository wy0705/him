package com.lmxdawn.him.api.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public Object set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return "set success";
    }
}
