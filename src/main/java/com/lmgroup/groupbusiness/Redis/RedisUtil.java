package com.lmgroup.groupbusiness.Redis;


import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

public class RedisUtil{

    private static Logger logger = Logger.getLogger(RedisUtil.class);
    private static JedisUtil jedisUtil=JedisUtil.getInstance();

    private static String host="127.0.0.1";
    private  static int port=6379;


    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            return jedisUtil.getJedis(host,port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放redis实例到连接池.
     * @param jedis redis实例
     */
    public  static void closeJedis(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
           // jedisUtil.closeJedis(jedis,RedisConfig.getHost(),RedisConfig.getPort());
        }
    }

}