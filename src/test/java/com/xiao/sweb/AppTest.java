package com.xiao.sweb;

import org.apache.commons.codec.binary.Hex;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppTest {
    // 计算 Sign
    private static String getSign(String partner_key, String atime, String user_id)
            throws NoSuchAlgorithmException {
        String info = partner_key + atime + user_id;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        md5.update(srcBytes);
        byte[] resultBytes = md5.digest();
        String resultString = new String(new Hex().encode(resultBytes));
        return resultString.substring(8, 24);
    }

    public static void main(String[] args) {
        try {
            // 合作方 partner_key， 注意不是 partner
//            String partner_key = "HdGtQUf4lkmTobNZ";
//            String partner_key = "yWaCTPOSozGNJnZq";
//            // UNIX TIMESTAMP 最小单位为秒
//            String atime = "1690422798";
//            // 第三方用户唯一标识，可以为字母与数字组合的字符串。
//            String user_id = "39637";
//            // 计算sign结果为: 5afda19c5d65a7a7
//            String sign = getSign(partner_key, atime, user_i9d);
//            System.out.println(sign);
            int number = 300/100;
            BigDecimal result = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(1.05)).setScale(1, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
            Integer data = result.intValue();
            System.out.println("结果：" + data);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
