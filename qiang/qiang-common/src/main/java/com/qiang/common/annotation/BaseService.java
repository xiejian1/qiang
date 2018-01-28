package com.qiang.common.annotation;

import java.lang.annotation.*;

/**
 * Created by xieqiang_daye on 2018/1/28.
 */
/**
 * 初始化继承BaseService的service
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
