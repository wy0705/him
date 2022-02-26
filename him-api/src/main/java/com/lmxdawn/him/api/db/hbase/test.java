package com.lmxdawn.him.api.db.hbase;//package com.mf.game.engine.db.hbase;
//
//import com.mf.game.engine.db.entry.User;
//import com.mf.game.engine.util.Contans;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.util.Bytes;
//
//import java.io.IOException;
//
//public class test {
//    public static void add(User user) throws IOException {
//            long id=HbaseUtil.getId(Contans.USER_TABLE,"info","User_Id","password");
//            System.out.println("user表添加成功");
//            Table table;
//            table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.USER_TABLE));
//            Put put=new Put(Bytes.toBytes("xxx"));
//            put.addColumn(Bytes.toBytes("xcc"),Bytes.toBytes("ccc"),Bytes.toBytes(id));
//            table.put(put);
//            table.close();
//            System.out.println("添加成功");
//    }
//    public static void main(String[] args) throws IOException {
//        User user=new User();
//        add(user);
//    }
//}
