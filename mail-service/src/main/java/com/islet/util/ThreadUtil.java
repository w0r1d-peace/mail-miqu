package com.islet.util;

import com.islet.model.base.User;

public class ThreadUtil {

    /**
     * 获取用户信息
     */
    public static ThreadLocal<User> currentUser = new ThreadLocal<>();

}
