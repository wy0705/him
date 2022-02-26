package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("equipRedis")
public class EquipRedis {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 保存装备到redis
     */
    public void saveEquipToRedis(String name, String type, String info, String energy, int money){
        redisTemplate.opsForValue().set(name,type+info+energy+money);
        //缓存一天
        redisTemplate.boundValueOps(name).set(type+info+energy+money,1, TimeUnit.DAYS);

    }
    /**
     * 删除装备
     */
    public void delEquipToRedis(String name,String value){
        redisTemplate.delete(name);

    }

    /**
     * 更新堡垒
     */
//    public boolean updateEquipToRedis(String Equip_Id,String newValue,String oldValue){
//        Long del=RedisTools.deleteValueOfList(Equip_Id,3,oldValue);
//        Long add=RedisTools.appendRightList(Equip_Id,newValue);
//        if (add==0){
//            return false;
//        }
//        return true;
//    }
}
