package com.example.spa.common.util;

import javax.servlet.http.HttpServletRequest;

public class NetUtils {
    public static String getIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}
