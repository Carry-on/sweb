package com.xiao.sweb.utills;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaoyu
 * @version 1.0
 * @date 2021/3/7 15:44
 */
public class IPUtil {

    public static final String UNKNOWN = "unknown";

    public static final String IP_SPLIT_COMMA = ",";

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是，有可能用户使用了代理软件方式避免真实IP地址
     * 可是，如果通过了多级反向代理的话，x-forwarded-for的值并不只一个，而是一串IP值
     */
    public static String  getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)){
            // 多次反向代理后会有多个ip值，第一个才是真实ip
            if (ip.contains(IP_SPLIT_COMMA)){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_ROT");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getRemoteUser();
        }
        return ip;
    }
}
