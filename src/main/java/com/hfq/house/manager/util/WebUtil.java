package com.hfq.house.manager.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class WebUtil {

    public static String getRootUrl() {
        HttpServletRequest request = getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        } else {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
    }
    
    /**
     * 获取当前用户ID
     * @return
     */
/*    public static Long getUserId() {
        StaffDto staff = AuthManager.getStaff();
        if (staff != null) {
            return staff.getId();
        } else {
            return 0L;
        }
    }*/
}
