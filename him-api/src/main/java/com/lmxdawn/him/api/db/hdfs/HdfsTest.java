package com.lmxdawn.him.api.db.hdfs;//package com.mf.game.engine.db.hdfs;


import com.lmxdawn.him.api.utils.Contans;

import java.io.IOException;

public class HdfsTest {
    public static void init() throws InterruptedException, IOException, ClassNotFoundException {
        HdfsUtil.createHdfs(Contans.USER_TABLE,"hdfs://IP地址/output");
//        HdfsUtil.createHdfs(Contans.RANKING_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.MERCHANT_FORT_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.MERCHANT_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.USER_ATTACK_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.USER_MERCHANT_FORT_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.NAME_SPACE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.EQUIP_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.FORT_TABLE,"hdfs://IP地址/output");
        HdfsUtil.createHdfs(Contans.USER_EQUIP_TABLE,"hdfs://IP地址/output");

    }
//运行一次即可
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        init();
    }
}
