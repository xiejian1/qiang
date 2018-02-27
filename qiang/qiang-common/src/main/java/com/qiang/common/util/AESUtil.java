package com.qiang.common.util;

import org.mybatis.spring.SqlSessionTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by xieqiang_daye on 2018/2/9.
 * AES加解密工具类
 */
public class AESUtil {
    private static  final String ENCODE_RULES = "zheng";

    /**
     * 加密
     * 1、构造秘钥生成器
     * 2、根据ecncodeRules规则初始化秘钥生成器
     * 3、产生秘钥
     * 4、创建和初始化密码器
     * 5、内容加密
     * 6、返回字符串
     * */
    public static String aesEncode(String content){
        try{
            //1、构造秘钥生成器，指定为AES算法，不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2、根据encodeRules规则初始化秘钥生成器
            // 生成一个128位的随机源，根据传入的字节数组
            SecureRandom radom = SecureRandom.getInstance("SHA1PRNG");
            radom.setSeed(ENCODE_RULES.getBytes());
            keyGenerator.init(128,radom);

            //3、产生原始对称秘钥
            SecretKey originalKey = keyGenerator.generateKey();
            //4、获得原始对称秘钥的字节数
            byte[]row = originalKey.getEncoded();
            //5、根据字节数组生成AES秘钥
            SecretKey key = new SecretKeySpec(row,"AES");
            //6、根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7、初始化密码器，第一个参数为加密（Decrypt_mode）操作或解密（Decrypt_mode）操作，第二个为使用的key
            cipher.init(Cipher.ENCRYPT_MODE,key);
            //8、获取加密内容的字节数组（设置为你utf-8）内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes("utf-8");
            //根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            //10、将加密后的数据转换为字符串
            //用Base64Encoder中会找到
            //解决办法
            //在项目中BUILD path 中先移除JRE system library,在添加JRE SYSTEM Library,然后重新编译一下乙炔正常
            String aesEncode = new String(new BASE64Encoder().encode(byteAES));
            //11、将字符串返回
            return aesEncode;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (NoSuchPaddingException e){
            e.printStackTrace();
        }catch (InvalidKeyException e){
            e.printStackTrace();
        }catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }catch (BadPaddingException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        //如果有错就返回null
        return null;
    }
    /**
     * 解密
     * 解密过程
     * 1.同加密1-4步骤
     * 2.将加密后的字符串反方成byte[]数组
     * 3.将加密内容解密
     * */
     public static String aesDecode(String content){
         try{
             //1、构造生成秘钥器,指定为aes算法
             KeyGenerator keygen = KeyGenerator.getInstance("AES");
             //2、根据encodeRules规则初始化秘钥生成器
             //生成一个128位的随机源，根据传入的字节数组
             SecureRandom radom = SecureRandom.getInstance("SHA1PRNG");
             radom.setSeed(ENCODE_RULES.getBytes());
             keygen.init(128,radom);
             //3、产生原始对称秘钥
             SecretKey originalKey = keygen.generateKey();
             //4、获得原始对称秘钥的数组
             byte [] raw = originalKey.getEncoded();
             //5、根据字节数组生成AES秘钥
             SecretKey key = new SecretKeySpec(raw,"AES");
             //6、根据指定算法AES生成密码器
             Cipher cipher = Cipher.getInstance("AES");
             //7、初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Ecrypt_mode),第三个参数为使用的key
             cipher.init(Cipher.DECRYPT_MODE,key);
             //8、将加密并编码后的内容解码城字节数组
             byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
             /**
              * 解密
              * */
             byte[] byteDecode = cipher.doFinal(byteContent);
             String aesDecode = new String(byteDecode,"utf-8");
             return aesDecode;
         }catch (NoSuchAlgorithmException e){
             e.printStackTrace();
         }catch (NoSuchPaddingException e){
             e.printStackTrace();
         }catch (InvalidKeyException e){
             e.printStackTrace();
         }catch (IOException e){
             e.printStackTrace();
         }catch (IllegalBlockSizeException e){
             throw new RuntimeException("对不起，配置文件中的密码需要使用AES加密，请使用com.qiang.common.util.AESUtil工具类修改这些值");
         }catch (BadPaddingException e){
             e.printStackTrace();
         }
         //如果有错就返回null
         return null;
     }
     public static void main(String [] args){
         String[] keys = {"","123456"};
         System.out.println("key|AESEncode|AESDecode");
         for(String key:keys){
             System.out.print(key+"|");
             String encryptString = aesEncode(key);
             System.out.print(encryptString+"|");
             String decryptString = aesDecode(encryptString);
             System.out.println(decryptString);
         }
     }
}
