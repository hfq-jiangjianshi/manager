package com.hfq.house.manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis工具类, 通过JedisUtil.getInstance()获取实例。 <br>
 * getJedis()取得Jedis实例，用完后要通过releasJedis()释放.
 * 
 * @author Jing Nianqiang
 */
public class JedisUtil {

    private static Logger          logger                       = LoggerFactory.getLogger(JedisUtil.class);

    /**
     * redis服务器地址
     */
    private String                 redisServerHost;
    private Integer                redisServerPort;
    private String                 redisServerPassword;
    private Integer                redisServerDb;
    private Integer                redisServerTimeout;

    /**
     * 最大连接数
     */
    public static final int        CONFIG_MAX_ACTIVE            = 500;

    /**
     * 最大空闲连接数，-1 表示无限制
     */
    public static final int        CONFIG_MAX_IDLE              = -1;

    /**
     * 取一个连接的最长阻塞时间, milliseconds. 此处设置为10秒
     */
    public static final int        CONFIG_MAX_WAITE_MILLISECOND = 1000;                                    // 10 seconds.

    /**
     * 连接池达到最大连接数后取连接的行为:此处为阻塞等待，直到有连接返回，或达到最大阻塞时间返回失败
     */
    //	public static final byte CONFIG_EXHAUSTED_ACTION = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;

    private static JedisPoolConfig config;

    private static JedisPool       pool;

    /**
     * 私有构造器，初始化连接池
     */
    public JedisUtil() {
        config = new JedisPoolConfig();
        config.setMaxTotal(CONFIG_MAX_ACTIVE);
        config.setMaxIdle(CONFIG_MAX_IDLE);
        config.setMaxWaitMillis(CONFIG_MAX_WAITE_MILLISECOND);
        config.setMinIdle(5);
        config.setTestWhileIdle(true);
        config.setTestOnBorrow(true);
        config.setBlockWhenExhausted(false);
        if (redisServerHost != null && redisServerPassword != null) {
            pool = new JedisPool(config, redisServerHost, redisServerPort, redisServerTimeout, redisServerPassword,
                    redisServerDb);
        }
    }

    public int set(String key, String value) {
        Jedis jedis = null;
        logger.info("[set]key=" + key + ",value=" + value);
        int ret = 0;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("", e);
            ret = -1;
        } finally {
            releaseJedis(jedis);
        }
        return ret;
    }

    /**
     * @param key
     * @param value
     * @param expireTime 过期时间（单位为‘秒’）
     * @return
     */
    public int set(String key, String value, int expireTime) {
        Jedis jedis = null;
        logger.info("jedis set key:{},value:{},expireTime:{}", key, value, expireTime);
        int ret = 0;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("", e);
            ret = -1;
        } finally {
            releaseJedis(jedis);
        }
        return ret;
    }

    public String get(String key) {
        Jedis jedis = null;
        logger.info("[get]key=" + key);
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            releaseJedis(jedis);
        }
        return value;
    }

    public boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            releaseJedis(jedis);
        }
        return false;
    }

    /**
     * 从顶端放入一条数据
     * 
     * @return
     */
    public int lpush(byte[] key, byte[] value) {
        Jedis jedis = null;
        //		logger.info("jedis lpush key:{}", key);
        int ret = 0;
        try {
            jedis = getJedis();
            jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("", e);
            ret = -1;
        } finally {
            releaseJedis(jedis);
        }
        return ret;
    }

    /**
     * 从底端pop一条数据
     * 
     * @return
     */
    public byte[] rpop(byte[] key) {
        Jedis jedis = null;
        //		logger.info("jedis rpop key:{}", key);
        byte[] ret = null;
        try {
            jedis = getJedis();
            ret = jedis.rpop(key);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            releaseJedis(jedis);
        }
        return ret;
    }

    /**
     * 查看指定key的list的长度
     * 
     * @return
     */
    public int llen(String key) {
        Jedis jedis = null;
        logger.info("jedis llen key:{}", key);
        Long ret = 0L;
        try {
            jedis = getJedis();
            ret = jedis.llen(key);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            releaseJedis(jedis);
        }
        return Integer.parseInt(String.valueOf(ret));
    }

    /**
     * 从连接池中获取Jedis
     * 
     * @return
     */
    public Jedis getJedis() {
        long beginTime = System.currentTimeMillis();
        Jedis jedis;
        if (pool == null) {
            pool = new JedisPool(config, redisServerHost, redisServerPort, redisServerTimeout, redisServerPassword,
                    redisServerDb);
        }

        jedis = pool.getResource();

        return jedis;
    }

    /**
     * 释放Jedis回连接池
     * 
     * @param jedis
     */
    public void releaseJedis(Jedis jedis) {
        long beginTime = System.currentTimeMillis();

        if (jedis != null) {
            try {
                jedis.close();
                // pool.returnResourceObject(jedis);
            } catch (Exception e) {
                logger.info("jedis.close", e);
            }
        } else {
            logger.error("try to release a jedis, but jedis is null");
        }
    }

    public void setRedisServerHost(String redisServerHost) {
        this.redisServerHost = redisServerHost;
    }

    public void setRedisServerPort(Integer redisServerPort) {
        this.redisServerPort = redisServerPort;
    }

    public void setRedisServerPassword(String redisServerPassword) {
        this.redisServerPassword = redisServerPassword;
    }

    public void setRedisServerDb(Integer redisServerDb) {
        this.redisServerDb = redisServerDb;
    }

    public void setRedisServerTimeout(Integer redisServerTimeout) {
        this.redisServerTimeout = redisServerTimeout;
    }

}
