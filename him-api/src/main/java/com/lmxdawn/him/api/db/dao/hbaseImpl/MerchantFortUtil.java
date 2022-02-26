package com.lmxdawn.him.api.db.dao.hbaseImpl;

import com.lmxdawn.him.api.db.entry.Merchant_Fort;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantFortUtil{
//    商户购买成功堡垒时添加一条数据
//    商户可以转卖堡垒，往redis中存入一条数据，转入成功删除一条数据
//    商户可以修改此堡垒的广告内容
//    查看堡垒显示的广告内容
    //商户注册成功创建表
    public static void crateMc(String tableName) throws IOException {
        HbaseUtil.createTable(tableName,new String[]{"merchant_info","fort_info"});
    }

    public Merchant_Fort add(String tableName,Merchant_Fort merchantFort) throws IOException {

        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(tableName));
        Put put=new Put(Bytes.toBytes(merchantFort.getRowKey()));
        put.addColumn(Bytes.toBytes("merchant_info"),Bytes.toBytes("mcId"),Bytes.toBytes(merchantFort.getMc_id()));
        put.addColumn(Bytes.toBytes("merchant_info"),Bytes.toBytes("mcName"),Bytes.toBytes(merchantFort.getMc_name()));
        put.addColumn(Bytes.toBytes("fort_info"),Bytes.toBytes("fortId"),Bytes.toBytes(merchantFort.getFort_id()));
        put.addColumn(Bytes.toBytes("fort_info"),Bytes.toBytes("fortName"),Bytes.toBytes(merchantFort.getFort_name()));

        table.put(put);
        table.close();
        return merchantFort;
    }
    //获得整个表的数据
    public void getAll(String tableName) throws IOException {
        HbaseUtil.queryAll(tableName);
    }

    //转让成功删除数据
    public void delete(String tableName,String rowKey) throws IOException {
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(tableName));
        Delete delete=new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
        System.out.println("删除成功");
    }

    public String getData(String tableName,String rowKey) throws IOException {
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(tableName));
        Get get=new Get(Bytes.toBytes(rowKey));
        Result result=table.get(get);
        String mcId=Bytes.toString(result.getValue(Bytes.toBytes("merchant_info"),Bytes.toBytes("mcId")));
        String mcName=Bytes.toString(result.getValue(Bytes.toBytes("merchant_info"),Bytes.toBytes("mcName")));
        String fortId=Bytes.toString(result.getValue(Bytes.toBytes("fort_info"),Bytes.toBytes("fortId")));
        String fortName=Bytes.toString(result.getValue(Bytes.toBytes("fort_info"),Bytes.toBytes("fortName")));

        return mcId+mcName+fortId+fortName;
    }

}
