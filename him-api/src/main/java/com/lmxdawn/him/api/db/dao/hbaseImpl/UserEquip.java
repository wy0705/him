package com.lmxdawn.him.api.db.dao.hbaseImpl;

import com.lmxdawn.him.api.db.entry.User_Equip;
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
public class UserEquip{
//    用户购买装备时往表中添加一条数据
//    用户可以查看自己的装备信息

    //创建用户装备表
    public static void crateE(String tableName) throws IOException {
        HbaseUtil.createTable(tableName,new String[]{"user_info","equip_info"});
    }
    public User_Equip add(String tableName,User_Equip user_equip) throws IOException {
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(tableName));
        Put put=new Put(Bytes.toBytes(user_equip.getUEId()));
        put.addColumn(Bytes.toBytes("user_info"),Bytes.toBytes("userId"),Bytes.toBytes(user_equip.getUserid()));
        put.addColumn(Bytes.toBytes("equip_info"),Bytes.toBytes("equipId"),Bytes.toBytes(user_equip.getEquip_id()));
        put.addColumn(Bytes.toBytes("equip_info"),Bytes.toBytes("equipName"),Bytes.toBytes(user_equip.getEquip_Name()));
        put.addColumn(Bytes.toBytes("equip_info"),Bytes.toBytes("equipInfo"),Bytes.toBytes(user_equip.getEquip_Info()));
        put.addColumn(Bytes.toBytes("equip_info"),Bytes.toBytes("equipEnergy"),Bytes.toBytes(user_equip.getEquip_Energy()));

        table.put(put);
        table.close();
        System.out.println("添加hbase成功");
        return user_equip;
    }
    //获取单条数据
    public void getData(String tableName,String rowKey) throws IOException{
        Table table = null;
        table=HbaseUtil.getConn().getTable(TableName.valueOf(tableName));
        Get get=new Get(Bytes.toBytes(rowKey));
        Result result=table.get(get);
        String userId=Bytes.toString(result.getValue(Bytes.toBytes("user_info"),Bytes.toBytes("userId")));
        String equipId=Bytes.toString(result.getValue(Bytes.toBytes("equip_info"),Bytes.toBytes("equipId")));
        String equipName=Bytes.toString(result.getValue(Bytes.toBytes("equip_info"),Bytes.toBytes("equipName")));
        String equipInfo=Bytes.toString(result.getValue(Bytes.toBytes("equip_info"),Bytes.toBytes("equipInfo")));
        String equipEnergy=Bytes.toString(result.getValue(Bytes.toBytes("equip_info"),Bytes.toBytes("equipEnergy")));

        System.out.println(userId+equipId+equipName+equipInfo+equipEnergy);

    }
    //查看整个表
    public void getAll(String tableName){
        HbaseUtil.queryAll(tableName);
    }
}
