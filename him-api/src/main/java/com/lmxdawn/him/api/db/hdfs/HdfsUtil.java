package com.lmxdawn.him.api.db.hdfs;//package com.mf.game.engine.db.hdfs;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HdfsUtil {

    public static Configuration config =null;
    static {
        config=HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "zookeeper IP地址");
        config.set("hbase.zookeeper.property.clientPort", "端口号");
        config.set("zookeeper.znode.parent", "/hbase");
    }
    public static void createHdfs(String tableName,String outPutpath) throws IOException, ClassNotFoundException, InterruptedException {

//        String tablename = "mr:test";// 表名字
//        String outputpath = "hdfs://IP地址/output";//输出路径


        Job job = Job.getInstance(config, "HbaseToHdfs");
        job.setJarByClass(HdfsUtil.class);
        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob(tableName, scan, doMapper.class, Text.class, Text.class, job);
        job.setReducerClass(WordCountHbaseReaderReduce.class);
        FileOutputFormat.setOutputPath(job, new Path(outPutpath));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class doMapper extends TableMapper<Text, Text> {
        protected void map(ImmutableBytesWritable key, Result value, Context context)
                throws IOException, InterruptedException {
            // 获取对应的字段
            String TOWNSHIP = new String(value.getValue("ColumnFamily1".getBytes(), "TOWNSHIP".getBytes()), "utf-8");

            context.write(new Text(""), new Text(TOWNSHIP));
        }
    }

    public static class WordCountHbaseReaderReduce extends Reducer<Text, Text, Text, Text> {
        private Text result = new Text();

        protected void reduce(Text key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {
            for (Text val : values) {
                result.set(val);
                context.write(key, result);
            }
        }
    }
}
