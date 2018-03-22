package com.qiang.common.util;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.util.Properties;

/**
 * Velocity工具类
 * Created by xieqiang_daye on 2018/3/12.
 */
public class VelocityUtil {


/***
 * 根据模板生成文件
 * @param inputVmFilePath
 * @param context
 * @param outputFilePath
 * @throw Exception
 * */
    public static void generate(String inputVmFilePath, String outputFilePath, VelocityContext context) throws  Exception{
        try{
            Properties properties = new Properties();
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,getPath(inputVmFilePath));
            Velocity.init(properties);
            //VelocityEngine engine = new VelocityEngine();
            Template template = Velocity.getTemplate(getFile(inputVmFilePath));
            File outputFile = new File(outputFilePath);
            FileWriterWithEncoding writer = new FileWriterWithEncoding(outputFile,"utf-8");
            template.merge(context,writer);
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * 根据文件绝对路径获取目录
     * @param filePath
     * @return
     * */
    public static String getPath(String filePath){
        String path = "";
        if(StringUtils.isNotBlank(filePath)){
            path = filePath.substring(0,filePath.lastIndexOf("/")+1);
        }
        return  path;
    }

    /**
     * 根据文件绝对路径获取文件
     * */
    public static String getFile(String filePath){
        String path = "";
        if(StringUtils.isNotBlank(filePath)){
            path = filePath.substring(0,filePath.lastIndexOf("/")+1);
        }
        return path;
    }
}
