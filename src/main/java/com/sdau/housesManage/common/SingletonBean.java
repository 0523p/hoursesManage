package com.sdau.housesManage.common;

import com.sdau.housesManage.entity.User;
import org.apache.shiro.SecurityUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonBean {

    public static Map<Integer, Object> userIdSessionMaps = new ConcurrentHashMap<>();

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getUserInfo() {
        return (User)SecurityUtils.getSubject().getSession().getAttribute("user");
    }
}