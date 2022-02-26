package com.lmxdawn.him.api.db.dao.hbaseImpl;

import com.lmxdawn.him.api.db.entry.User_Attack;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAttackUtil extends HbaseUtil {
//    用户注册成功往用户往此表（用户攻击力表）添加一条数据为用户的初始攻击能力
//    当用户购买装备时在，用户装备表添加数据，对应的用户ID添加装备id,
//    当用户购买装备成功时对用户攻击力表的数据进行修改相应的值
//    以及对应的信息,并且更改用户攻击信息表的数据信息，装备信息为，装备的名字，用户攻击伤害根据需要进行增加

    public void add(String name) throws IOException {
        //通过name获取用户的ID，然后添加ID到用户攻击力表
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ID_TABLE));
        Get get=new Get(Bytes.toBytes(name));
        get.addColumn(Bytes.toBytes("id"),Bytes.toBytes("id"));
        Result result=table.get(get);
        table.close();
        User_Attack ua=new User_Attack();
        //赋值攻击力为10
        int attcak=10;
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ATTACK_TABLE));
        Put put=new Put(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("id")));
        put.addColumn(Bytes.toBytes("attack_info"),Bytes.toBytes("attcak"),Bytes.toBytes(attcak));
        table1.put(put);
        table1.close();
        System.out.println("攻击力获取");

    }

    //更改武力值
    public void update(String name) throws IOException {
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ID_TABLE));
        Get get=new Get(Bytes.toBytes(name));
        get.addColumn(Bytes.toBytes("id"),Bytes.toBytes("id"));
        Result result=table.get(get);
        table.close();
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ATTACK_TABLE));
        Get get1=new Get(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("id")));
        Result result1=table1.get(get1);
        int attack=Bytes.toInt(result1.getValue(Bytes.toBytes("attack_info"),Bytes.toBytes("attack")));
        attack=attack+10;
        //获取之前武力值，增加武力值10,然后更新数据库数据
        Put put=new Put(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("id")));
        put.addColumn(Bytes.toBytes("attack_info"),Bytes.toBytes("attcak"),Bytes.toBytes(attack));
        table1.put(put);
        table1.close();
    }

    //获取用户武力值
    public int getAttack(String name) throws IOException {
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ID_TABLE));
        Get get=new Get(Bytes.toBytes(name));
        get.addColumn(Bytes.toBytes("id"),Bytes.toBytes("id"));
        Result result=table.get(get);
        table.close();
        Table table1=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_ATTACK_TABLE));
        Get get1=new Get(result.getValue(Bytes.toBytes("id"),Bytes.toBytes("id")));
        Result result1=table1.get(get1);
        int attack=Bytes.toInt(result1.getValue(Bytes.toBytes("attack_info"),Bytes.toBytes("attack")));
        return attack;
    }

}
