package com.wyyx.cn.consumer.untils.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/21
 * Time: 19:11
 * Description: No Description
 */
public class ip {
    // java 后台获取访问客户端ip地址
//
//    protected String getClientIpAddress(HttpServletRequest request) {
//        String clientIp = request.getHeader("x-forwarded-for");
//        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getHeader("Proxy-Client-IP");
//        }
//        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
//            clientIp = request.getRemoteAddr();
//        }
//        return clientIp;
//    }
}
