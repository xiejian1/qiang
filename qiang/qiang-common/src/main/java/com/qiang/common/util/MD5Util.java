package com.qiang.common.util;

import java.security.MessageDigest;

/**
 * Created by xieqiang_daye on 2018/2/19.
 */
public class MD5Util {

    //用于加密的字符串
    public final static String md5(String content){
    char[] md5String={'0','1','2','3','4','5','6','7','8','9',
    'A','B','C','E','F'};
    try{
        //使用平台的默认字符集将此String编码为byte序列，并将结果存储到一个新的byte序列中
        byte[]bInput = content.getBytes();
        //信息摘要是安全的单向哈希函数，他接收任意大小的数据，并输出固定长度的哈希值
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        //MessageDigest对象通过使用update方法处理函数，使用指定的byte数组更新摘要
        mdInst.update(bInput);
        //摘要更新之后，通过调用digest()执行哈希计算，获得密文
        byte[] md = mdInst.digest();
        //把密文转换成十六进制的字符串形式
        int j = md.length;
        char[] str = new char [j*2];
        int k=0;
        for(int i=0;i<j;i++){ //i=0
            byte byte0 = md[i];//95
            str[k++] = md5String[byte0 >>> 4&0xf];//5
            str[k++] = md5String[byte0&0xf];  //F
        }
        //返回经过加密后的字符串
        return  new String(str);
    }catch (Exception e){
        return null;
    }
    }
}
