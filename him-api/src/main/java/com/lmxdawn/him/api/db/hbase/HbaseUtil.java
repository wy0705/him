package com.lmxdawn.him.api.db.hbase;

//import com.mf.game.engine.redis.JedisUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseUtil {
    public static final String HDFS_PATH = "hdfs://192.168.243.132:9000";
    public static Configuration config =null;
//    public static Jedis jedis=null;
    private Connection connection=null;
//    public static JedisUtil jedisUtil=jedisUtil.getInstance();

//    public static JedisUtil jedisUtil=null;
    private static class SingletonHolder{
        private static final HbaseUtil INSTANCE=new HbaseUtil();
    }
    public HbaseUtil(){
        config=HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            connection=ConnectionFactory.createConnection(config);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        return SingletonHolder.INSTANCE.connection;
    }
    static {
//        HBaseConfiguration.create();
        config=HBaseConfiguration.create();
//        config.set("hbase.rootdir", "hdfs://192.168.243.132:9000/hbase");
        config.set("hbase.rootdir", "hdfs://192.168.124.116:9000/HBase");
        config.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
        config.set("hbase.zookeeper.property.clientPort", "2181");
    }
    /**
     * 创建命名空间
     */

    public static void createNameSpace(String namespace) throws IOException {
        //获取hbase的配置信息
//        Configuration configuration=HBaseConfiguration.create();
        //获取hbase的管理员对象
        Configuration conf=HBaseConfiguration.create();
        Connection connection=ConnectionFactory.createConnection(config);
        Admin admin=connection.getAdmin();
        //构造命名空间描述器
        NamespaceDescriptor namespaceDescriptor=NamespaceDescriptor.create(namespace).build();
        //创建namespace
        admin.createNamespace(namespaceDescriptor);

        admin.close();
        connection.close();
    }
    /**
     * 创建表
     */
    public static void createTable(String tableName, String[] familys) throws IOException {

        try {
            //获取hbase的配置信息
            Configuration configuration=HBaseConfiguration.create();
            //获取hbase的管理员对象
            Connection connection=ConnectionFactory.createConnection(configuration);
            Admin admin=connection.getAdmin();
            //表名
            TableName tName=TableName.valueOf(tableName);
            if (admin.tableExists(tName)){
                System.out.println(tName+"该表已经创建，请建立别的表");
            }else {
                // HTableDescriptor hTableDescriptor=new HTableDescriptor(TableName.valueOf(tableName));
                TableDescriptorBuilder tableDB=TableDescriptorBuilder.newBuilder(tName);
                for (int i = 0; i <familys.length ; i++) {
//                ColumnFamilyDescriptor cloumnFD=ColumnFamilyDescriptor

                    ColumnFamilyDescriptorBuilder columnFDB=ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familys[i]));
                    columnFDB.setBlockCacheEnabled(true);
                    columnFDB.setMaxVersions(5);
                    columnFDB.setInMemory(true);
                    ColumnFamilyDescriptor family=columnFDB.build();
                    tableDB.setColumnFamily(family);

                }
                TableDescriptor td=tableDB.build();
                admin.createTable(td);

                System.out.println("创建表表明"+tableName+"成功！");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//id自增
    public static long getId(String tableName,String family,String rowkey,String colum) {
        long id = 0l;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            Table table = hc.getTable(TableName.valueOf(tableName));
            id = table.incrementColumnValue(Bytes.toBytes(rowkey), Bytes.toBytes(family),
                    Bytes.toBytes(colum), 1);
            System.out.println(id);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;

    }
    public void createTableSplit(String tname,String[] familys){
        try {
            Connection hc = ConnectionFactory.createConnection(config);

            Admin admin = hc.getAdmin();
            TableName tableName = TableName.valueOf(tname);
            if (admin.tableExists(tableName)) {
                System.out.println(tableName
                        + "表已经创建，请创建其他表");
            } else {
                TableDescriptorBuilder  tdb  =TableDescriptorBuilder.newBuilder(tableName);
                TableDescriptor desc = tdb.build();
                for (int i = 0; i < familys.length; i++) {
                    ColumnFamilyDescriptorBuilder cdb =ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familys[i]));
                    cdb.setInMemory(true);
                    ColumnFamilyDescriptor family=cdb.build();
                    tdb.setColumnFamily(family);
                }
//				admin.createTable(desc);
                admin.createTable(desc, "a0".getBytes(), "a10000".getBytes(), 3);
                System.out.println("创建 \'" + tableName + "\' 表成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createTableSplit2(String tbname, String[] familys) {
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            Admin admin = hc.getAdmin();
            TableName tableName = TableName.valueOf(tbname);
            if (admin.tableExists(tableName)) {
                System.out.println(tableName
                        + "表已经创建，请创建其他表");
            } else {
                TableDescriptorBuilder  tdb  =TableDescriptorBuilder.newBuilder(tableName);
                TableDescriptor desc = tdb.build();
                for (int i = 0; i < familys.length; i++) {
                    ColumnFamilyDescriptorBuilder cdb =ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familys[i]));
                    ColumnFamilyDescriptor family=cdb.build();
                    tdb.setColumnFamily(family);
                }
                byte[][] regions = new byte[][]{Bytes.toBytes("a3333333"),
                        Bytes.toBytes("a6666666"), Bytes.toBytes("a99999999")};
                // 表示有三个region分别放入key：
                // [1] start key: , end key: A
                // [2] start key: A, end key: D
                // [3] start key: D, end key:
                admin.createTable(desc, regions);
                System.out.println("创建 \'" + tableName + "\' 表成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    删除表

    public void deleteTable(String tbname) {
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            Admin admin = hc.getAdmin();
            TableName tableName = TableName.valueOf(tbname);
            if (!admin.tableExists(tableName)) {
                System.out.println(tableName + "不存在");
            } else {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
                System.out.println(tableName + " 删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//获取表内容
    public Table getTable(String tbname) throws IOException {
        //新连接池写法
        Connection hc = ConnectionFactory.createConnection(config);
        TableName tableName = TableName.valueOf(tbname);
        Table table = hc.getTable(tableName);
        return table;
    }
    //    删除数据通过表名和rowkey删除
    public void deleteData(String tbname, String rowKey) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(tbname);
            table = hc.getTable(tableName);


            Delete del = new Delete(Bytes.toBytes(rowKey));
            table.delete(del);
            System.out.println("delete a data successful");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //添加数据
    public void insertData(String tbname, String rowKey, String family,
                           String qualifier, String value) {
        //表名、行键、列族、列、值
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(tbname);
            table = hc.getTable(tableName);
//            long ts=System.currentTimeMillis();

//            //行键用时间戳+。。。生成
//            String rowkey1=rowKey+ts;

            List rows = new ArrayList();
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            Put put1 = new Put(Bytes.toBytes(rowKey));
            put1.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put1.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            rows.add(put1);
            table.put(rows);
            System.out.println("添加成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    查询
    public static void queryAll(String tbname) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(tbname);
            table = hc.getTable(tableName);

            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            for(Result rs:scanner) {
                String rowkey = Bytes.toString(rs.getRow());
                System.out.println("row key :"+rowkey);
                Cell[] cells  = rs.rawCells();
                for(Cell cell : cells) {
                    System.out.println(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+"::"+Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())+"::"+
                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                }
                System.out.println("-----------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //获取单条数据
    public static void getData(String tbname,String rowKey) throws IOException{
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(tbname);
            table = hc.getTable(tableName);
            Get get = new Get(rowKey.getBytes());
            //利用get对象信息获取对应的结果result
            Result result = table.get(get);
            //获取rowkey
            byte[] row = result.getRow();
            //获取cell
            byte[] name = result.getValue("cf1".getBytes(), "name".getBytes());
            System.out.println(Bytes.toString(name));
            //row的类型是字节数组，所以应该借助Bytes转换成字符串输出
            System.out.println(Bytes.toString(row));
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
