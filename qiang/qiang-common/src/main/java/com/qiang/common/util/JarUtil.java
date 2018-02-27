package com.qiang.common.util;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by xieqiang_daye on 2018/2/20.
 */
public class JarUtil {
    public static synchronized void decompress(String fileName,String outputPath){
        //保证输出路径为目录
        if(!outputPath.endsWith(File.separator)){
            outputPath+=File.separator;
        }
        //如果不存在目录，先创建目录
        File dir= new File(outputPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //解压到输出目录
        JarFile jf = null;
        try{
            jf = new JarFile(fileName);
            for(Enumeration<JarEntry>e=jf.entries();e.hasMoreElements();){
                JarEntry je=e.nextElement();
                String outFileName = outputPath+je.getName();
                File f= new File(outFileName);
                if (je.isDirectory()){
                    if (!f.exists()){
                        f.mkdirs();
                    }
                }else {
                    File pf = f.getParentFile();
                    if(!pf.exists()){
                        pf.mkdirs();
                    }
                    InputStream in= jf.getInputStream(je);
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                    byte[] buffer = new byte[2048];
                    int nBytes;
                    while((nBytes = in.read(buffer)) > 0){
                        out.write(buffer,0,nBytes);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        }catch (Exception e){
           System.out.print("解压"+fileName+"出错!"+e.getMessage());
        }finally {
            if (jf!=null){
                try{
                    jf.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
