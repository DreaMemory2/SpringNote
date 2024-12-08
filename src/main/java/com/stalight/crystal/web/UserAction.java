package com.stalight.crystal.web;

import com.stalight.crystal.service.UserService;
import com.stalight.crystal.service.impl.UserServiceImpl;

/**
 * <p>表示层</p>
 */
public class UserAction {
    private static final UserService USER_SERVICE = new UserServiceImpl();

    /**
     * <p>删除用户信息请求</p>
     */
    public void deleteUserRequest() {
        USER_SERVICE.deleteUserInfo();
    }
}
