package com.lmxdawn.him.api.db.service;

import com.lmxdawn.him.api.db.dao.hbaseImpl.UserAttackUtil;
import com.lmxdawn.him.api.db.dao.hbaseImpl.UserEquip;
import com.lmxdawn.him.api.db.dao.hbaseImpl.UserUtil;
import com.lmxdawn.him.api.db.dao.redisImpl.UserRedis;
import com.lmxdawn.him.api.db.entry.User_Attack;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserRedis userRedis;
    @Autowired
    private UserAttackUtil userAttackUtil;
    @Autowired
    private UserEquip userEquip;
//注册用户名，手机号，密码放入hbase

    public void Register(String name,String phone,String password) throws IOException {
        userUtil.add(name, phone, password);
        System.out.println("添加成功");
        userRedis.LoginNamePassword(name, password);
        System.out.println("加入缓存成功");
        //用户注册成功获得初始武力值
        userAttackUtil.add(name);
        //生产装备表
        UserEquip.crateE(name+"Equip");
    }

    //用户先到redis查看没有再到hbase
    public int login(String name,String password) throws IOException {
        int result=0;
        Object map=redisTemplate.opsForValue().get(name);

        System.out.println("xxxx"+map);
        if (map==null){
            System.out.println("缓存中没有数据");
            //查询数据库
            if (userUtil.loginUser(name, password)==true){
                result=1;
                System.out.println("xxxx登录成功");
                userRedis.LoginNamePassword(name, password);
                System.out.println("xxxx加入缓存成功");
            }else {
                System.out.println("登录失败");
            }
        }if (map!=null){
            if (password==map.toString()){
                System.out.println("通过缓存登录成功");
                result=1;
            }
        }else {
            System.out.println("error");
        }
        return result;
    }
}
