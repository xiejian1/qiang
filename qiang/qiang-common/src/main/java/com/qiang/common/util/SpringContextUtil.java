package com.qiang.common.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 资源文件读取工具
 * Created by xieqiang_daye on 2018/2/6.
 */
public class SpringContextUtil implements ApplicationContextAware{

    private  static ApplicationContext context = null;
    private SpringContextUtil(){
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    /**
     * 根据名称获取bean
     * @param
     * @return
     * */
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
    /**
     * 根据bean名称获取指定类型的bean
     * @param clazz
     * @param beanName
     * @return
     * */
    public  static <T> T getBean(String beanName,Class<T> clazz){
        return context.getBean(beanName,clazz);
    }
    /**
     * 根据指定类型获取bean
     * @param clazz
     * */
    public static <T> T getBean(Class<T> clazz){
        T t = null;
        Map<String,T> map = context.getBeansOfType(clazz);
        for(Map.Entry<String,T> entry:map.entrySet()){
            t = entry.getValue();
        }
        return t;
    }
    /**
     * 是否包含bean
     * */
    public static  boolean containsBean(String beanName){
        return context.containsBean(beanName);
    }
    /**
     * 是否是单列
     * */
    public static boolean isSingleBean(String beanName){
        return context.isSingleton(beanName);
    }
    /**
     * bean的类型
     * @param beanName
     * @return
     * */
    public  static Class getType(String beanName){
        return context.getType(beanName);
    }

}
