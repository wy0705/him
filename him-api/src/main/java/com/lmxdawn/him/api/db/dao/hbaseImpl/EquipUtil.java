package com.lmxdawn.him.api.db.dao.hbaseImpl;


import com.lmxdawn.him.api.db.entry.Equip;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("equipHbaseImpl")
public class EquipUtil extends HbaseUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    private static Logger logger = LoggerFactory.getLogger(HbaseUtil.class);

    //    装备有增加装备
//    查看装备信息
//1、整表分页查看
//    2、点开装备图片单独查看
    public Equip add(String name, String type, String info, String energy, int money) throws IOException {
        Equip equip=new Equip();
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.EQUIP_TABLE));

        Put put=new Put(Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("type"),Bytes.toBytes(type));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("info"),Bytes.toBytes(info));
        put.addColumn(Bytes.toBytes("value"),Bytes.toBytes("energy"),Bytes.toBytes(energy));
        put.addColumn(Bytes.toBytes("value"),Bytes.toBytes("money"),Bytes.toBytes(money));

        table.put(put);
        table.close();
        System.out.println("装备添加成功");

        redisTemplate.opsForValue().set(name,type+"|"+info+"|"+energy+"|"+money);
        System.out.println("添加缓存成功");
        return equip;

    }
    //查询所有装备的信息
    public void query(){
        HbaseUtil.queryAll(Contans.EQUIP_TABLE);
    }

    //查看单独一条数据

//    @Override
    public void Data(String rowKey) throws IOException {
        Table table = null;
        try {
            table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.EQUIP_TABLE));

            Get get = new Get(Bytes.toBytes(rowKey));
            //利用get对象信息获取对应的结果result
            Result result = table.get(get);
            String type=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("type")));
            String info=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("info")));
            String energy=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("energy")));
            String money=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("money")));

            System.out.println(rowKey+"|"+type+"|"+info+"|"+energy+"|"+money);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
