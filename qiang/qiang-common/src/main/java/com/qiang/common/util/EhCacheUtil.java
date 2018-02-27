package com.qiang.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**Ehcache工具类
 * Created by xieqiang_daye on 2018/2/20.
 */
public class EhCacheUtil {
    /**
     * 获取缓存
     * @param cacheName
     * */
    private static Cache getCache(String cacheName){
        CacheManager cacheManager = CacheManager.getInstance();
        if(null==cacheManager){
            return  null;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if(null==cache){
            return null;
        }
        return cache;
    }
    /**
     * xinzeng缓存记录
     *@param cacheNme
     * @param value
     * @param key
     * */
    public static void put(String cacheNme,String key,Object value){
        Cache cache = getCache(cacheNme);
        if(null==cache){
            Element element = new Element(key,value);
            cache.put(element);
        }
    }
    /**
     * 删除缓存记录
     * @param key
     * @param cacheName
     * */
    public static boolean remove(String cacheName,String key){
        Cache cache = getCache(cacheName);
        if(null==cache){
            return  false;
        }
        return cache.remove(key);
    }
    /**
     * 删除全部缓存记录
     * @param cacheName
     * */
    public static void removeAll(String cacheName){
        Cache cache =getCache(cacheName);
        if(null!=cache){
            cache.removeAll();
        }
    }
    /**
     * 获取缓存记录
     * @param cacheName
     * @param key
     * @return
     * */
    public static Object get(String cacheName,String key){
        Cache cache = getCache(cacheName);
        if(null==cache){
            return null;
        }
        Element cacheElement = cache.get(key);
        if(null==cacheElement){
            return  null;
        }
        return cacheElement.getObjectValue();
    }
}
