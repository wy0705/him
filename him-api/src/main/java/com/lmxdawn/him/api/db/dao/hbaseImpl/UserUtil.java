package com.lmxdawn.him.api.db.dao.hbaseImpl;

import com.lmxdawn.him.api.db.entry.User;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Repository
public class UserUtil{

    @Autowired
    private RedisTemplate redisTemplate;
//    1、用户通过手机号密码登录，用户通过用户名密码登录
//    2、用户注册，用户名手机号密码确认密码(手机号等录获得验证码）
//    3、注册成功立即生成一个用户攻击力表（userattack）设置初始值，为10攻击力

    //    用户表的增删改查
    //    增加数据
//注册用户


    public User add(String name, String phone, String password) throws IOException {

        User user=new User();
        long id= HbaseUtil.getId(Contans.USER_TABLE,"info","UserId","UserId");
        System.out.println(id);
        String key="user"+id;
        Table table;
        //向用户表添加数据
        table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_TABLE));
        Put put=new Put(Bytes.toBytes(id));
//        put.addColumn(Bytes.toBytes("rowkey"),Bytes.toBytes("userId"),Bytes.toBytes(id));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("phone"),Bytes.toBytes(phone));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("password"),Bytes.toBytes(password));
        table.put(put);
        table.close();
        System.out.println("user表添加数据成功");

        table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ID_TABLE));
        Put put1=new Put(Bytes.toBytes(name));
        put1.addColumn(Bytes.toBytes("id"),Bytes.toBytes("id"),Bytes.toBytes(id));
        System.out.println("装入user_id表成功");
        table.put(put1);
        table.close();
//        redisTemplate.opsForValue().set(key,name+"|"+phone+"|"+password);
//        redisTemplate.boundValueOps(key).set();
        System.out.println(key);
        System.out.println("添加缓存成功");
        return user;
    }

    //通过用户名和密码登录
    public boolean loginUser(String name,String password) throws IOException {

        boolean flag=false;
        //获得表连接
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ID_TABLE));

        System.out.println(table);
        //通过用户名获取rowkey id
        Get get=new Get(Bytes.toBytes(name));
        get.addColumn(Bytes.toBytes("id"),Bytes.toBytes("id"));
        System.out.println("xxxx"+get);
        Result result=table.get(get);
        table.close();
        //连接user表
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_TABLE));

        get=new Get(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("id")));

        System.out.println("bbbb"+result.getRow());
        Result result1=table1.get(get);
        table1.close();

        //判断从hbase获取的密码是否等于输入的密码
        String getpassword=Bytes.toString(result1.getValue(Bytes.toBytes("info"),Bytes.toBytes("password")));

        if (getpassword==null){
            System.out.println(getpassword);
            System.out.println("请注册");
        }else
        if (getpassword.equals(password)){
            flag=true;
            System.out.println("登录成功");
        }
        else {
            flag=false;
        }
        return flag;
    }

    }
