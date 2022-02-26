package com.lmxdawn.him.api.db.dao.hbaseImpl;


import com.lmxdawn.him.api.db.entry.Fort;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;

@Service("fortHbaseImpl")
public class FortUtil extends HbaseUtil {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
//    堡垒表进行增加堡垒ok
//    查看堡垒，可以堡垒休息通过地理位置查找，使用es geo
    //查看所以堡垒
    public Fort add(Fort fort) throws IOException {
        fort.getFort_id();
        fort.setFort_Energy(1000);
        Integer randomId = new Random().nextInt(1000000);
        fort.setFort_id(Long.valueOf(randomId));
        System.out.println(Long.valueOf(randomId));
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.FORT_TABLE));
        Put put=new Put(Bytes.toBytes(fort.getFort_id()));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(fort.getFort_Name()));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("energy"),Bytes.toBytes(fort.getFort_Energy()));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("place"),Bytes.toBytes(fort.getPlace()));
        table.put(put);
        table.close();
        System.out.println("添加堡垒成功");

        //添加数据到es
        IndexQuery indexQuery=new IndexQueryBuilder()
                .withIndexName("fort")
                .withType("_doc")
                .withId(fort.getFort_id().toString())
                .withObject(fort)
                .build();

        elasticsearchTemplate.index(indexQuery);
        return fort;
    }
//查看所有堡垒从数据库查
    public void query(){
        HbaseUtil.queryAll(Contans.EQUIP_TABLE);
    }
//    @Override
//    public void queryAll(String tbname) {
//        Table table = null;
//        try {
//            table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.FORT_TABLE));
//            Scan scan = new Scan();
//            ResultScanner scanner = table.getScanner(scan);
//            for(Result rs:scanner) {
//                String rowkey = Bytes.toString(rs.getRow());
//                System.out.println("row key :"+rowkey);
//                Cell[] cells  = rs.rawCells();
//                for(Cell cell : cells) {
//                    System.out.println(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+"::"+Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())+"::"+
//                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
//                }
//                System.out.println("-----------------------------------------");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                table.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void setData(String RowKey) throws IOException {

        Table table = null;
        try {
            table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.FORT_TABLE));
            Get get = new Get(Bytes.toBytes(RowKey));
            //利用get对象信息获取对应的结果result
            Result result = table.get(get);
            String name=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("name")));
            String energy=Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("energy")));
            System.out.println(RowKey+"|"+name+"|"+energy);
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

    //装备表分页

}
