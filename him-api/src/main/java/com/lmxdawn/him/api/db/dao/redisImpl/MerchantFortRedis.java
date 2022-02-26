package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;
//
//import com.mf.game.engine.db.entry.Merchant_Fort;
//import com.mf.game.engine.util.JsonUtil;
//import com.mf.game.engine.util.RedisTools;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("merchantRedis")
//public class MerchantFortRedis {
//    //商户转让堡垒时把堡垒信息放入redis
//    //获取商户堡垒信息到redis
//
//    /**
//     * 获取所有商户和堡垒信息
//     * @param key
//     * @return
//     */
//    public List getAllMerchantFort(String key){
//        if (key==null){
//            return null;
//        }
//        List<String> list= RedisTools.getList(key);
//        List<Merchant_Fort> mf=new ArrayList<>();
//
//        if (list!=null){
//            for (String string:list){
//                Merchant_Fort mfort= JsonUtil.changeStringToMFort(string);
//                mf.add(mfort);
//            }
//
//        }else {
//            return null;
//        }
////        List MerchantFort=new ArrayList();
////        for (String string:list){
////            String[] split=string.split("\\"+"|");// |属特殊字符，需转义
////            Merchant_Fort mf=new Merchant_Fort();
////            mf.setRowKey(split[0]);
////            mf.setMc_id(split[1]);
////            mf.setMc_name(split[2]);
////            mf.setFort_id(split[3]);
////            mf.setFort_name(split[4]);
////            MerchantFort.add(mf);
////        }
//        return mf;
//    }
//    /**
//     * 保存商户堡垒信息到redis
//     * key:rowKey
//     * value:
//     */
//    public boolean saveMFToRedis(String key,String value){
//        Long retureSize=RedisTools.appendLeftList(key,value);
//        if (retureSize==0){
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 删除商户堡垒
//     * key:rowKey
//     * value:
//     */
//    public boolean delMFToRedis(String key,String value){
//        Long del=RedisTools.deleteValueOfList(key,1,value);
//        if (del==0){
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 更新堡垒商户内容
//     * key:rowkey
//     * oldvalue
//     * oldvalue
//     */
//    public boolean updateMFToRedis(String key,String newValue,String oldValue){
//        Long delListValue=RedisTools.deleteValueOfList(key,1,oldValue);
//        Long addRList=RedisTools.appendRightList(key,newValue.trim());
//        if (addRList==0) {
//            return false;
//        }
//        return true;
//    }
//}
