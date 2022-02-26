package com.lmxdawn.him.api.db.service;

import com.lmxdawn.him.api.db.dao.hbaseImpl.MerchantUtil;
import com.lmxdawn.him.api.db.dao.redisImpl.MerchantRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MerchantService {
    @Autowired
    private RedisTemplate redisTemplate;

    private MerchantUtil merchantUtil;

    private MerchantRedis merchantRedis;

    public void Register(String name,String password) throws IOException {
        merchantUtil.add(name, password);
        System.out.println("注册成功");
        merchantRedis.LoginMer(name, password);
        System.out.println("加入缓存成功");
    }

    public void Login(String name,String password) throws IOException {
        Object o=redisTemplate.opsForValue().get(name);
        if (o==null){

            System.out.println("缓存没有数据");
            if (merchantUtil.loginMer(name, password)==true){
                System.out.println("登录成功");
                merchantRedis.LoginMer(name, password);
                System.out.println("添加缓存成功");
            }else {
                System.out.println("登录失败");
            }
        }if (o!=null){
            if (password==o.toString()){
                System.out.println("登录成功");
            }

        }else {
            System.out.println("error");
        }
    }
}
