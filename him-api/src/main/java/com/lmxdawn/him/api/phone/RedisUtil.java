package com.lmxdawn.him.api.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO

 **/
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key
     * @return 获得值
     * redis有五种数据类型 opsForValue表示是操作字符串类型
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * 本来只可以放入string类型，但是我们配置了自动序列化所以这儿可以传入object
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 原子操作
     *
     * @param key
     * @param value
     * @param expire 过期时间 秒
     * @return
     */
    public boolean setex(String key, Object value, long expire) {
        try {
            //TimeUnit.SECONDS指定类型为秒
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MICROSECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 非原子操作
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        try {
            //这儿没有ops什么的是因为每种数据类型都能设置过期时间
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param key
     * @return 获取key的过期时间
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * @param keys 删除key 可变参数
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(keys));
        }
    }

    /**
     * @param key
     * @param step 传入正数 就是加多少 传入负数就是减多少
     * @return
     */
    public long incrBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * @param key
     * @param value
     * @return 如果该key存在就返回false 设置不成功 key不存在就返回ture设置成功
     */
    public boolean setnx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 原子操作
     *
     * @param key
     * @param value
     * @param expire 在上面方法加上过期时间设置
     * @return
     */
    public boolean setnxAndExpire(String key, Object value, long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @param value
     * @return 如果该key存在就返回之前的value  不存在就返回null
     */
    public Object getAndSet(String key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * @param key
     * @return 判断key是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}