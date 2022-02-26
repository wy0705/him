package com.lmxdawn.him.api.db.hbase;

import com.lmxdawn.him.api.utils.Contans;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service("createTable")
public class CreateHbaseTable {

//    public static void init() throws IOException {
//        HbaseUtil.createNameSpace(Contans.NAME_SPACE);
//        //    1、创建用户表
//        HbaseUtil.createTable(Contans.USER_TABLE, new String[]{"info"});
//        //    2、装备表
//        HbaseUtil.createTable(Contans.EQUIP_TABLE,new String[]{"info","value"});
//        //    3、堡垒表
//        HbaseUtil.createTable(Contans.FORT_TABLE,new String[]{"info"});
//        //    4、用户装备表
//        HbaseUtil.createTable(Contans.USER_EQUIP_TABLE,new String[]{"user_info","equip_info"});
//
//        //    5、商户表
//        HbaseUtil.createTable(Contans.MERCHANT_TABLE,new String[]{"info"});
//        //    6、商户堡垒表
//        HbaseUtil.createTable(Contans.MERCHANT_FORT_TABLE,new String[]{"merchant_info","fort_info"});
//        //    7、用户攻击信息表
//        HbaseUtil.createTable(Contans.USER_ATTACK_TABLE,new String[]{"user_info","attack_info"});
//        //    8、用户商户堡垒关系表
//        HbaseUtil.createTable(Contans.USER_MERCHANT_FORT_TABLE,new String[]{"user_info","merchant_info","fort_info"});
//
////        HbaseUtil.createTable(Contans.RANKING_TABLE,new String[]{"user","ranking"});
//    }
//运行一次即可

    public static void main(String[] args) throws IOException {
//        init();
//        HbaseUtil.createNameSpace(Contans.NAME_SPACE);
//            1、创建用户表
//        HbaseUtil.createTable(Contans.USER_TABLE, new String[]{"info"});
//            2、装备表
//        HbaseUtil.createTable(Contans.EQUIP_TABLE,new String[]{"info","value"});
//        //    3、堡垒表
//        HbaseUtil.createTable(Contans.FORT_TABLE,new String[]{"info"});
//        //    4、用户装备表
//        HbaseUtil.createTable(Contans.USER_EQUIP_TABLE,new String[]{"user_info","equip_info"});

        HbaseUtil.createTable(Contans.Mer_id,new String[]{"id"});
//        //    5、商户表
//        HbaseUtil.createTable(Contans.MERCHANT_TABLE,new String[]{"info"});
//        //    6、商户堡垒表
//        HbaseUtil.createTable(Contans.MERCHANT_FORT_TABLE,new String[]{"merchant_info","fort_info"});
//        //    7、用户攻击信息表
        HbaseUtil.createTable(Contans.USER_ATTACK_TABLE,new String[]{"attack_info"});
//        //    8、用户商户堡垒关系表
//        HbaseUtil.createTable(Contans.USER_MERCHANT_FORT_TABLE,new String[]{"user_info","merchant_info","fort_info"});
//
//        HbaseUtil.createTable(Contans.RANKING_TABLE,new String[]{"user","ranking"});
//        HbaseUtil.createTable(Contans.USER_ID_TABLE,new String[]{"id"});
    }

}
