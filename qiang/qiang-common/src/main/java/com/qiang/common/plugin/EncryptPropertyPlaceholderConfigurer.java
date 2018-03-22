package com.qiang.common.plugin;

import com.qiang.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 支持加密配置文件插件
 * Created by xieqiang-daye on 2018/3/22.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

    private String[] propertyNames = {
            "master.jdbc.password", "slave.jdbc.password", "generator.jdbc.password", "master.redis.password"
    };

    /**
     * 解密指定propertyName的加密属性值
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        for (String p : propertyNames) {
            if (p.equalsIgnoreCase(propertyName)) {
                return AESUtil.aesDecode(propertyValue);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}
