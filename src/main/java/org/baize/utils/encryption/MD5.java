package org.baize.utils.encryption;

import org.baize.utils.LoggerUtils;

import java.security.MessageDigest;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
public class MD5 {
    public static String md5(String instr){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            LoggerUtils.getLogicLog().error("加密失败",e);
            return "";
        }
        char[] charArr = instr.toCharArray();
        byte[] byteArr = new byte[charArr.length];
        for (int i = 0;i<charArr.length;i++) {
            byteArr[i] = (byte) (charArr[i]);
        }
        byte[] md5Bytes = md5.digest(byteArr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<md5Bytes.length;i++){
            int var = ((int)md5Bytes[i]) & 0xff;
            if(var < 6)
                sb.append("0");
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

    /**
     * 可逆加密算法
     * @param instr
     * @return
     */
    public static String encode(String instr){
        char[] a = instr.toCharArray();
        for (int i = 0;i<a.length;i++){
            a[i] = (char)(a[i] ^ 't');
        }
        String e = new String(a);
        return e;
    }

    public static String decode(String instr){
        char[] a = instr.toCharArray();
        for (int i = 0;i<a.length;i++){
            a[i] = (char)(a[i] ^ 't');
        }
        String d = new String(a);
        return d;
    }

    public static void main(String[] args) {
        String s = new String("a");
        System.out.println("原始:"+s);
        System.out.println("md5后:"+md5(s));
        System.out.println("md5后再加密:"+encode(s));
        System.out.println("解密:"+decode(s));
    }
}
