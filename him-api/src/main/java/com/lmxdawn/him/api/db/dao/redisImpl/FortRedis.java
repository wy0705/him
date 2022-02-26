package com.lmxdawn.him.api.db.dao.redisImpl;


import com.lmxdawn.him.api.db.entry.Fort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("fortRedis")
public class FortRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取堡垒到redis
     */
    public void add(Fort fort){
        redisTemplate.opsForValue().set(fort.getFort_id(),fort.getFort_Name()+fort.getGeo()+fort.getFort_Energy()+fort.getPlace());
        //过期时间
        redisTemplate.boundValueOps(fort.getFort_id()).set(fort.getFort_Name()+fort.getGeo()+fort.getFort_Energy()+fort.getPlace(),1, TimeUnit.DAYS);
    }
    /**
     * 从redis删除
     * key:FortId
     */
    public void delete(Fort fort){
        redisTemplate.delete(fort.getFort_id());
    }
    /**
     * 缓存一组fort
     * 缓存一点到范围内的堡垒信息
     */
//    public List<Fort> addList(Fort fort){
//        redisTemplate.opsForList();
//    }

}
