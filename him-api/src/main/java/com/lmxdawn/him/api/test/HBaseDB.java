package com.lmxdawn.him.api.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseDB {
    private Configuration conf = null;
    private Connection conn = null;
    //单例模式
    private static class SingletonHolder {
        private static final HBaseDB INSTANCE = new HBaseDB();
    }
    //将链接放到了构造函数中
    private HBaseDB() {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "oracle");
        conf.set("hbase.rootdir", "hdfs://oracle:9000/hbase");
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static Connection getConn() {
            return SingletonHolder.INSTANCE.conn;
    }

    //id自增
    public static long getId(String tableName,String family,String rowkey,String colum) {
        long id = 0l;
        try {
            Table table = getConn().getTable(TableName.valueOf(tableName));
            id = table.incrementColumnValue(Bytes.toBytes(rowkey), Bytes.toBytes(family), 
                    Bytes.toBytes(colum), 1);
            System.out.println(id);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;

    }
}
