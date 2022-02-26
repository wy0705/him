package com.lmxdawn.him.api.test;//package com.mf.game.engine.test;
//
//import com.mf.game.engine.test.JedisUtil;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.util.Bytes;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Pipeline;
//
//import java.io.IOException;
//
//public class TestHabse {
//
//    //与HBase数据库的连接对象
//    public static Connection connection;
//
//    //数据库元数操作对象
//    public static Admin admin;
//    public static Jedis jedis = null;
//
//    public static JedisUtil JedisUtil;
//    public static JedisUtil getInstance() {
//        if(JedisUtil == null){
//            JedisUtil = new JedisUtil();
//        }
//        return JedisUtil;
//    }
//
//    public static void main(String[] args) {
//        try {
//            System.out.println("开始执行本程序");
//            System.out.println("HBaseDemo.main()->admin1:" + admin);
//            synchronousDataByHbaseToRedis();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public static void synchronousDataByHbaseToRedis() throws IOException {
//        Configuration config = HBaseConfiguration.create();
//        //设置连接参数:hbase数据库所在的主机IP
//        config.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
//        //设置连接参数:hbase数据库使用的接口
//        config.set("hbase.zookeeper.property.clientPort", "2181");
//
//        Connection conn = ConnectionFactory.createConnection(config);
//        // 创建一个数据库管理员
//
//        Table table = conn.getTable(TableName.valueOf("company_invest"));
//        try {
//            Scan scan = new Scan();
//
//
//// 扫某一列
//            scan.addColumn(Bytes.toBytes("“data”"), Bytes.toBytes("“invests”"));
//            scan.addColumn(Bytes.toBytes("“data”"), Bytes.toBytes("“keyno”"));
//            ResultScanner resultScanner = table.getScanner(scan);
//            jedis = JedisUtil.getJedis();
//            Pipeline pip = jedis.pipelined();
//            int level = 1;
//            for (Result r : resultScanner) {
//                String keyno = Bytes.toString(r.getValue(Bytes.toBytes("data"), Bytes.toBytes("keyno")));
//                String name = Bytes.toString(r.getValue(Bytes.toBytes("data"), Bytes.toBytes("invests")));
//                pip.setex(keyno, 60 * 60 * 24, name);
//
//// for (Cell kv : r.rawCells()) {undefined
//// String keyNo = Bytes.toString( kv.getRow());
//// String value = Bytes.toString(CellUtil.cloneValue(kv));
//// System.out.println(keyNo +"===========" +value);
//// }
//                System.out.println(level++);
//
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            conn.close();
//            jedis.close();
//        }
//    }
//
//
//}