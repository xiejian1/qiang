package com.qiang.common.listener;

import com.qiang.common.annotation.BaseService;
import com.qiang.common.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xieqiang_daye on 2018/1/28.
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    private static  final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //应用参数的根目录
        if(null==contextRefreshedEvent.getApplicationContext().getParent()){
            LOGGER.debug(">>>>> spring初始化wanbi <<<<<<<<<<<");
            //spring 初始化完毕后，通过反射调用所有使用BaseSrervice注解的initMapper方法
            Map<String,Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for (Object service:baseServices.values()){
                LOGGER.debug(">>>>>>>>> {}.initMapper()",service.getClass().getName());
                try {
                    Method initMapper = service.getClass().getMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    LOGGER.error("初始化BaseService的initMapper方法异常",e);
                    e.printStackTrace();
                }
            }
            /**
             * 系统入口初始化
             * */
            Map<String,BaseInterface> baseInterfaceBeans = contextRefreshedEvent.getApplicationContext().getBeansOfType(BaseInterface.class);
            for (Object service:baseInterfaceBeans.values()){
                 LOGGER.debug(">>>>>>>>> {}.init()",service.getClass().getName());
                try {
                    Method init = service.getClass().getMethod("init");
                    init.invoke(service);
                } catch (Exception e) {
                    LOGGER.error("初始化BaseInterface的init方法异常");
                    e.printStackTrace();
                }
            }
        }

    }

}
