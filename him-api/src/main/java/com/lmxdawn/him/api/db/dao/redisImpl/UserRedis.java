package com.lmxdawn.him.api.db.dao.redisImpl;


import com.lmxdawn.him.api.db.dao.hbaseImpl.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//
@Service("userRedis")
public class UserRedis {
    @Autowired
    private RedisTemplate redisTemplate;
    private UserUtil userUtil;
    public void LoginNamePassword(String name,String password){
        redisTemplate.opsForValue().set(name,password);
        redisTemplate.boundValueOps(name).set(password,24, TimeUnit.HOURS);
    }
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
    public void xx(){
//        userUtil.add(redisTemplate.opsForValue().get())
    }
//
//    /**
//     * 获取用户
//     */
//    public List getUser(String userId){
//        if (userId==null) {
//            return null;
//        }
//        List<String> list = RedisTools.getList(userId);
//        List<User> users = new ArrayList<>();
//        if (list!=null) {
//            for (String string : list) {
//                User user = JsonUtil.changerStringToUser(string);
//                users.add(user);
//            }
//        }else{
//            return null;
//        }
//        return users;
//    }
//    /**
//     * 保存用户到redis
//     */
//    public boolean saveUserToRedis(String userId, String value) {
//        RedisTools.deleteValueOfList(userId, 5,value);
//        Long returnSize = RedisTools.appendLeftList(userId, value);
//        if (returnSize == 0) {
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 从分布式缓存读取用户信息
//     * 用户登录
//     */
//    public boolean getLoginInfo(String userId,String password){
//        boolean bl=false;
//        String loginUser=RedisTools.get(userId);
//        if (loginUser!=null){
//            String[] split=loginUser.split("\\"+"|");
//            if (password.equals(split[0])){
//                bl=true;
//            }
//        }
//        return bl;
//    }
////    public boolean getLoginPhone(String phone){
////        boolean bl=false;
////        String login=RedisTools.get(phone);
////    }
//    /**
//     * 删除用户
//     */
//    public boolean delUserToRedis(String userId, String value) {
//        Long lrem = RedisTools.deleteValueOfList(userId, 5, value);
//        if (lrem==0) {
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 更新用户
//     */
//    public boolean updateUserToRedis(String userId, String newValue, String oldValue) {
//        Long deleteListValue = RedisTools.deleteValueOfList(userId, 5, oldValue.trim());
//        Long addRList = RedisTools.appendRightList(userId, newValue.trim());
//        if (addRList==0) {
//            return false;
//        }
//        return true;
//    }
}
