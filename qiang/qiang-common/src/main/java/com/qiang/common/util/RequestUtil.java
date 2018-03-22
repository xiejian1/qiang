package com.qiang.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * reuqest工具类
 * Created by xieqiang-daye on 2018/3/22.
 */
public class RequestUtil {

    /**
     * 移除指定的request参数
     * @param parameter
     * @return
     * */
    public String removeParam(HttpServletRequest request,String parameter){
        String queryString ="";
        Enumeration keys = request.getParameterNames();
        while(keys.hasMoreElements()){
            String key = (String) keys.nextElement();
            if(key.equals(parameter)){
                continue;
            }
            if ("".equals(queryString)){
                queryString = key+"="+request.getParameter(key);
            }else {
                queryString += "&"+key+"="+request.getParameter(parameter);
            }
        }
        return  queryString;
    }
    /**
     * 获取basepath
     * @param request
     * */
    public static String getBasePath(HttpServletRequest request){
        StringBuffer basePath = new StringBuffer();
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port= request.getServerPort();
        basePath.append(scheme);
        basePath.append("://");
        basePath.append(domain);
        if("http".equalsIgnoreCase(scheme)&&80!=port){
            basePath.append(":").append(String.valueOf(port));
        }else if ("http".equalsIgnoreCase(scheme)&&443!=port){
            basePath.append(":").append(String.valueOf(port));
        }
        return  basePath.toString();
    }

    /**
     * 获取ip工具类
     * @param request
     * */
    public  static String getAddr(HttpServletRequest request){
        String ip = request.getHeader("Cdn-Src-Ip");//网宿cdn的真实ip
        if(ip==null||ip.length()==0||"unnown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");//蓝汛cdn的真实ip
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Forwarded-For");//获取代理ip
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-Ip");//获取代理ip
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");//获取代理ip
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();//获取真实ip
        }
        return  ip;
    }
    /**
     * 请求中参数转Map<String,String>,for支付宝异步回调，建议直接使用request.getparameter()
     * 返回map<String,String>
     * */
    public static Map<String,String> getParameterMap(HttpServletRequest request){
        Map<String,String> result = new HashMap<String,String>();
        Enumeration parameterParams = request.getParameterNames();
        while(parameterParams.hasMoreElements()){
            String parameter = (String) parameterParams.nextElement();
            result.put(parameter,request.getParameter(parameter));
        }
        return  result;
    }
}
