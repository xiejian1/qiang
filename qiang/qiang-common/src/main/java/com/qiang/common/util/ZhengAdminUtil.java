package com.qiang.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压zhengadmin-x.x.x.jar到resources目录
 * Created by xieqiang-daye on 2018/3/22.
 */
public class ZhengAdminUtil implements InitializingBean,ServletContextAware{

    private static final Logger logger = LoggerFactory.getLogger(ZhengAdminUtil.class);


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
       logger.info("=====开始解压zheng-admin==========");
       String version = PropertiesFileUtil.getInstance("zheng-admin-client").get("zheng-admin-version");
       logger.info("zheng-admin.jar版本：{}",version);
       String jarPath = servletContext.getRealPath("/WEB-INF/lib/zheng-admin-"+version+".jar");
       logger.info("zheng-admin.jar 包路径：{}",jarPath);
       String resources = servletContext.getRealPath("/")+"/resources/zheng-admin";
       logger.info("zheng-admin.jar 解压到：{}",resources);
       JarUtil.decompress(jarPath,resources);
       logger.info("====解压zheng-admin完成========");
    }
}
