package com.lmxdawn.him.api.db.dao.redisImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class MerchantRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    public void LoginMer(String name,String password){
        redisTemplate.opsForValue().set(name,password);
        redisTemplate.boundValueOps(name).set(password,24, TimeUnit.HOURS);
    }

}
