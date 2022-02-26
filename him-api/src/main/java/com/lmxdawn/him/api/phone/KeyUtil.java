package com.lmxdawn.him.api.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

public class KeyUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    public static String keyUtils() {
        String str="0123456789";
        StringBuilder st=new StringBuilder(6);
        for(int i=0;i<6;i++){
            char ch=str.charAt(new Random().nextInt(str.length()));
            st.append(ch);
        }
        String findkey=st.toString().toLowerCase();
        return findkey;
    }

}

