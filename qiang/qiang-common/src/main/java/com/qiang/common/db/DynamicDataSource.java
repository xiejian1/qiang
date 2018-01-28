package com.qiang.common.db;

/**
 * Created by xieqiang_daye on 2018/1/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *动态数据源配置
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);
    private final static  ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    /**
     * 设置数据源
     * @param dataSource
     * */
    public static void setDataSource(String dataSource){
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 获取数据源
     * */
    public static String getDataSource(){
        String dataSource = CONTEXT_HOLDER.get();
        //如果没有指定数据源，默认数据源
        if (null == dataSource) {
          DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getDefault());
        }
        return  CONTEXT_HOLDER.get();
    }
    /**
     * 清除数据源
     * */
    public static void clearDataSource(){
        CONTEXT_HOLDER.remove();
    }
}
