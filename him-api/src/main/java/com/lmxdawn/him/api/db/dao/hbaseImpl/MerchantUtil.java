package com.lmxdawn.him.api.db.dao.hbaseImpl;


import com.lmxdawn.him.api.db.entry.Merchant;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantUtil extends HbaseUtil {

    //增加商户信息，增加广告内容
//    商户注册，商户登录
//添加广告内容

    public Merchant add(String name, String password) throws IOException {
        Merchant merchant=new Merchant();
        long id=HbaseUtil.getId(Contans.MERCHANT_TABLE,"info","Mer_id","Merid");
        System.out.println(id);
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.MERCHANT_TABLE));
        Put put=new Put(Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(password));
//        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(ad));

        table.put(put);
        table.close();
        System.out.println("商户注册成功");
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.Mer_id));
        Put put1=new Put(Bytes.toBytes(name));
        put1.addColumn(Bytes.toBytes("id"),Bytes.toBytes("mer_id"),Bytes.toBytes(id));
        System.out.println("索引成功");
        table1.put(put1);
        table1.close();
        return merchant;
        //创建一个商户对应的商户堡垒表
    }

    //登录
    public boolean loginMer(String name,String password) throws IOException {
        boolean flag=false;
        //获得表连接
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.Mer_id));

        System.out.println(table);
        //通过商户名获取rowkey id
        Get get=new Get(Bytes.toBytes(name));
        get.addColumn(Bytes.toBytes("id"),Bytes.toBytes("mer_id"));
        System.out.println("mer"+get);
        Result result=table.get(get);
        table.close();
        //连接商户表
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_TABLE));

        get=new Get(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("mer_id")));

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
//    在定义一个广告表

}
