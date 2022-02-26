package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;
//
//import com.mf.game.engine.db.entry.User_Equip;
//import com.mf.game.engine.util.JsonUtil;
//import com.mf.game.engine.util.RedisTools;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("UserEquipRedis")
//public class UserEquipRedis {
//    /**
//     * 获取用户
//     */
//    public List getUserEquip(String UEId){
//
//        if (UEId==null) {
//            return null;
//        }
//        List<String> list = RedisTools.getList(UEId);
//        List<User_Equip> ues = new ArrayList<>();
//        if (list!=null) {
//            for (String string : list) {
//                User_Equip ue = JsonUtil.changeStringToUE(string);
//                ues.add(ue);
//            }
//        }else{
//            return null;
//        }
//        return ues;
//    }
//    /**
//     * 保存到redis
//     */
//    public boolean saveUEToRedis(String UEId, String value) {
//        RedisTools.deleteValueOfList(UEId, 5,value);
//        Long returnSize = RedisTools.appendLeftList(UEId, value);
//        if (returnSize == 0) {
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 删除
//     */
//    /**
//     * 更新用户装备
//     */
//    public boolean updateUEToRedis(String UEId, String newValue, String oldValue) {
//        Long deleteListValue = RedisTools.deleteValueOfList(UEId, 5, oldValue.trim());
//        Long addRList = RedisTools.appendRightList(UEId, newValue.trim());
//        if (addRList==0) {
//            return false;
//        }
//        return true;
//    }
//}
