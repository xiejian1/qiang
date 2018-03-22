package com.qiang.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 * Created by xieqiang_daye on 2018/3/1.
 */
public class StringUtil {


    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]]");

    /***
     * 下划线转舵峰
     * @param str
     * */
    public static String lineToHump(String str){
        if(null==str||"".equals(str)){
            return  str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0,1).toUpperCase() + str.substring(1);

        return  str;
    }
    /***
     * 舵峰转下划线,效率比上面高
     *
     * */
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    /***
     *舵峰转下划线(简单写法，效率低)
     * @param str
     * */
    public  static String humpToLine2(String str){
        return str.replaceAll("[A-Z]]","_$0").toLowerCase();
    }
    /**
     * 首字母转小写
     * */
    public static String toLowerCaseFirstOne(String str){
        if(StringUtils.isBlank(str)){
            return  str;
        }
        if(Character.isLowerCase(str.charAt(0))){
            return  str;
        }else {
            return  (new StringBuffer()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }
    /**
     * 首字母转大写
     * @param str
     * */
    public static String toUpperCaseFirstOne(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }
        if(Character.isUpperCase(str.charAt(0))){
            return str;
        }else {
            return (new StringBuffer()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * Object转String
     * @param object
     * */
    public static String getString(Object object){
        return getString(object,"");
    }
    public static String getString(Object object,String defaultValue){
        if(null==object){
            return defaultValue;
        }
        try{
            return  object.toString();
        }catch (Exception e){
            return defaultValue;
        }
    }
    /**
     * object转Integer
     * */
    public static int getInt(Object object){
        return getInt(object,-1);
    }
    public static int getInt(Object object,Integer defaultValue){
        if (null ==object){
            return defaultValue;
        }
        try{
            return Integer.parseInt(object.toString());
        }catch (Exception e){
            return defaultValue;
        }
    }
    /**
     * objetc 转boolean
     * */
    public static boolean getBoolean(Object object){
        return getBoolean(object,false);
    }
    public static boolean getBoolean(Object object,Boolean defaultValue ){
        if (null==object){
            return defaultValue;
        }
        try{
            return Boolean.parseBoolean(object.toString());
        }catch (Exception e){
            return defaultValue;
        }
    }
}
