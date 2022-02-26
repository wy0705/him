package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;
//
//import com.mf.game.engine.db.entry.User_Attack;
//import com.mf.game.engine.util.JsonUtil;
//import com.mf.game.engine.util.RedisTools;
//
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("userAttackRedis")
//public class UserAttackRedis {
//    /**
//     * 获取用户攻击力
//     * key:userId
//     */
//    public List<User_Attack> getUA(String userId){
//        if (userId==null){
//            return null;
//        }
//        List<String> list= RedisTools.getList(userId);
//        List<User_Attack> ua=new ArrayList<>();
//
//        if (list!=null){
//            for (String string:list){
//                User_Attack uat= JsonUtil.changerStringToUA(string);
//                ua.add(uat);
//            }
//        }
//        else {
//            return null;
//        }
//        return ua;
//    }
//    /**
//     * 保存到redis
//     */
//    public boolean saveUAToRedis(String userId,String value){
//        RedisTools.deleteValueOfList(userId,3,value);
//        Long size=RedisTools.appendLeftList(userId,value);
//        if (size==0){
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 更新
//     */
//    public boolean updateUAToRedis(String userId,String newValue,String oldValue){
//        Long deleteListValue = RedisTools.deleteValueOfList(userId, 3, oldValue.trim());
//        Long addRList = RedisTools.appendRightList(userId, newValue.trim());
//        if (addRList==0) {
//            return false;
//        }
//        return true;
//    }
//
//}
