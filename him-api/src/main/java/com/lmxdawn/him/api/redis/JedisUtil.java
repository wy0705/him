package com.lmxdawn.him.api.redis;//package com.mf.game.engine.redis;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//public class JedisUtil {
//    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
//
//    private static JedisPool jedisPool = null;
//
////    private JedisUtil() {
////    }
//
////    public static JedisUtil JedisUtil = JedisUtil.getInstance();
//    static {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(6000000);
//        config.setMaxIdle(10000);
//        config.setMinIdle(8);
//        config.setMaxWaitMillis(100000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        config.setTestOnBorrow(true);
//        config.setTestWhileIdle(true);
//        config.setTimeBetweenEvictionRunsMillis(30000);
//        config.setNumTestsPerEvictionRun(10);
//        config.setMinEvictableIdleTimeMillis(60000);
////        主机安装redis
//        jedisPool = new JedisPool(config, "192.168.243.1", 6379, 10000, "157845");
//    }
//
//    /**
//
//     从jedis连接池中获取获取jedis对象
//     @return
//     */
//    public Jedis getJedis() {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//            jedis.close();
//        }
//        return jedis;
//    }
//}
