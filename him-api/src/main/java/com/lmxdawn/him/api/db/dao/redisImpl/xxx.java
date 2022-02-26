package com.lmxdawn.him.api.db.dao.redisImpl;//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.HashSet;
//
//@Bean
//
//@Primary
//
//public CacheManager cacheManager(RedisTemplate redisTemplate) {
//
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//
//        Mapexpires = new HashMap<>();
//
//        expires.put("timeout", 60L);
//
//// 设置超时
//
//// 根据特定名称设置有效时间
//
//        redisCacheManager.setExpires(expires);
//
//// 设置默认的时间
//
//        redisCacheManager.setDefaultExpiration(cacheDefaultExpiration);
//
//        return redisCacheManager;
//
//        }
//
//
//@Configuration
//
//protected(maxInactiveIntervalInSeconds = 3600 * 12)//最大过期时间
//
//@EnableCaching
//
//public class RedisConfig {
//
//    @Bean
//
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//
//        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//
////设置缓存过期时间
//
//        Mapexpires = new HashMap<>();
//
//        expires.put("12h", 3600 * 12L);
//
//        expires.put("1h", 3600 * 1L);
//
//        expires.put("10m", 60 * 10L);
//
//        rcm.setExpires(expires);
//
//// rcm.setDefaultExpiration(60 * 60 * 12);//默认过期时间
//
//        return rcm;
//
//    }
//
//}
//
////----------------------------------------------------------
//
//    @Cacheable(value = "12h", key = "#root.methodName")
//
//    @Override
//
//    public ListgetUserArticleRank() {
//
////获得排行榜前10名的用户，每12小时刷新一次
//
//        return userRepository.findTop10ByArticleSize();
//
//    }
///**
//
// * 2.XX版本的配置
//
// *
//
// */
//
//@Bean
//
//public CacheManager cacheManager(RedisConnectionFactory factory) {
//
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig(); // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//
//        config = config.entryTtl(Duration.ofMinutes(2)) // 设置缓存的默认过期时间，也是使用Duration设置
//
//        .disableCachingNullValues(); // 不缓存空值
//
//// 设置一个初始化的缓存空间set集合
//
//        SetcacheNames = new HashSet<>();
//
//        cacheNames.add("catalog_test_id");
//
//        cacheNames.add("catalog_test_name");
//
//// 对每个缓存空间应用不同的配置
//
//        MapconfigMap = new HashMap<>();
//
//        configMap.put("catalog_test_id", config);
//
//        configMap.put("catalog_test_name", config.entryTtl(Duration.ofMinutes(5)));
//
//        RedisCacheManager cacheManager = RedisCacheManager.builder(factory) // 使用自定义的缓存配置初始化一个cacheManager
//
//        .initialCacheNames(cacheNames) // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//
//        .withInitialCacheConfigurations(configMap)
//
//        .build();
//
//        return cacheManager;
//
//        }
