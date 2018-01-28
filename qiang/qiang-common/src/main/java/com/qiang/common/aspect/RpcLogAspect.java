package com.qiang.common.aspect;

/**
 * Created by xieqiang_daye on 2018/1/28.
 */

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rpc提供者和消费者日志打印
 * */
public class RpcLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcLogAspect.class);

    //开始时间
    private Long startTime = 0L;
    //结束时间
    private Long endTime = 0L;

    @Before("execution(* *..rpc..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint){
        LOGGER.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }
    @After("execution(* *..rpc..*.*(..))")
    public void doAfterInServiceLayer(){
        LOGGER.debug("doAfterInServiceLayer");
    }
    @Around("execution(* *..rpc..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Object result = pjp.proceed();
        //是否需要消费段
        boolean consumerSide = RpcContext.getContext().isConsumerSide();
        //获取最后一次提供方或调用方IP
        String ip = RpcContext.getContext().getRemoteHost();
        //服务URL
        String rpcUrl = RpcContext.getContext().getUrl().getParameter("application");
        LOGGER.info("consumerSide={},ip={},url={}",consumerSide,ip,rpcUrl);

        return result;
    }
}
