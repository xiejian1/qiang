package com.qiang.common.util;

import org.apache.activemq.transaction.Synchronization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**Redis工具类
 * Created by xieqiang-daye on 2018/3/22.
 */
public class RedisUtil {
   protected  static ReentrantLock lockPool = new ReentrantLock();
   protected  static ReentrantLock lockJedis = new ReentrantLock();

   private  static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

   //redis服务器ip
    private  static  String IP = PropertiesFileUtil.getInstance("redis").get("master.redis.ip");
    //redis端口号
    private  static  int PORT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.port");
    //访问密码
    private static String PASSWORD = AESUtil.aesDecode(PropertiesFileUtil.getInstance("redis").get("master.redis.password"));
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private  static int MAX_ACTIVE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active");
    //private static int MAX_ACTIVE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active");

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_idle");

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_wait");

    // 超时时间
    private static int TIMEOUT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.timeout");
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;

    private static JedisPool jedisPool = null;

    /**
     * redis过期时间,以秒为单位
     */
    // 一小时
    public final static int EXRP_HOUR = 60 * 60;
    // 一天
    public final static int EXRP_DAY = 60 * 60 * 24;
    // 一个月
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30;

    //初始化redis连接池
    private static void initialPool(){
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,IP,PORT,TIMEOUT);
        }catch (Exception e){
            logger.error("First create JedisPool error:"+e);
        }
    }
    //在多线程环境中同步初始化
    private  static synchronized void poolInit(){
        if(null==jedisPool){
            initialPool();
        }
    }
    //同步获取jedis实例
    /**
     *
     * @param
     * @return jedis
     * */
    public synchronized static Jedis getJedis(){
        poolInit();
        Jedis jedis = null;
        try{
            if(null!=jedisPool){
                jedis = jedisPool.getResource();
                try{
                    jedis.auth(PASSWORD);
                }catch (Exception e){

                }
            }
        }catch (Exception e){
            logger.error("Get jedis error:"+ e);
        }
        return jedis;
    }

    /**
     * 设置String
     * @param key
     * @param value
     * */
    public synchronized  static void set(String key ,String value){
        try{
            value = org.apache.commons.lang.StringUtils.isBlank(value)?"":value;
            Jedis jedis  = getJedis();
            jedis.set(key,value);
            jedis.close();
        }catch (Exception e){
            logger.error("Set key error:"+e);
        }
    }
    /**
     * 设置数组byte[]
     * @param key
     * @param value
     * */
    public synchronized static void set(byte[] key,byte[] value){
        try{
            Jedis jedis = getJedis();
            jedis.set(key,value);
            jedis.close();
        }catch (Exception e){
            logger.error("Set key error:"+e);
        }
    }
    /**
     * 设置数组过期时间
     * @param value
     * @param key
     * @param seconds
     * */
    public synchronized  static void set(String key,String value,int seconds){
        try{
            value = org.apache.commons.lang.StringUtils.isBlank(value)?"":value;
            Jedis jedis = getJedis();
            jedis.setex(key,seconds,value);
            jedis.close();
        }catch (Exception e){
        logger.error("Set keyex error:"+e);
        }
    }
    /**
     * 设置byte过期时间
     * @param seconds
     * @param key
     * @param value
     * */
    public synchronized  static void set(byte[] key,byte[] value,int seconds){
        try{
            Jedis jedis = getJedis();
            jedis.set(key,value);
            jedis.expire(key,seconds);
            jedis.close();
        }catch (Exception e){
            logger.error("Set key eoor:"+e);
        }
    }
    /***
     *获取String的值
     * @param key
     * @return value
     */
    public synchronized static String get(String key){
            Jedis jedis = getJedis();
            if(jedis==null){
                return  null;
            }
            String value=jedis.get(key);
            jedis.close();
            return value;
    }
    /**
     * 获取byte[]
     * @param key
     * @return
     * */
    public synchronized static byte[] get(byte[] key){
        Jedis jedis = getJedis();
        if (jedis==null){
            return null;
        }
        byte [] value = jedis.get(key);
        jedis.close();
        return value;
    }
    /**
     * 删除值
     * @param key
     * */

    public synchronized static void remove(String key){
        try{
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        }catch (Exception e){
            logger.error("remove key error:"+e);
        }
    }
    /**
     * 删除key byte[]
     * @param key
     * */
    public  synchronized static void remove(byte[] key){
        try{
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        }catch (Exception e){
            logger.error("remove key error:"+e);
        }
    }
    /**
     * 移除jedid
     * @param key
     * @param strings
     * */
    public synchronized  static void lpush(String key,String... strings){
        try{
            Jedis jedis = getJedis();
            jedis.lpush(key,strings);
            jedis.close();
        }catch (Exception e){
            logger.error("lpush error:"+e);
        }
    }
    /**
     * lrem
     *@param key
     * @param value
     * @param count
     * */
    public synchronized static  void lrem(String key,long count,String value){
        try{
            Jedis jedis = RedisUtil.getJedis();
            jedis.lrem(key,count,value);
            jedis.close();
        }catch (Exception e){
            logger.error("lrem error:"+e);
        }
    }
    /**
     * sadd
     * @param value
     * @param key
     * @param seconds
     * */
    public synchronized  static void sadd(String key,String value,int seconds){
        try{
            Jedis jedis  = RedisUtil.getJedis();
            jedis.sadd(key,value);
            jedis.expire(key,seconds);
            jedis.close();
        }catch (Exception e){
            logger.error("sadd error :"+e);
        }
    }
    /***
     *incr
     *@param key
     * */

    public synchronized static Long incr(String key){
        Jedis jedis = getJedis();
        if(null==jedis){
            return  null;
        }
        Long value = jedis.incr(key);
        jedis.close();
        return  value;
    }
    /**
     * decr
     * @param key
     * @return
     * */
    public  synchronized  static Long der(String key){
        Jedis jedis = getJedis();
        if(null==jedis){
            return  null;
        }
        Long value = jedis.decr(key);
        return  value;
    }
}
