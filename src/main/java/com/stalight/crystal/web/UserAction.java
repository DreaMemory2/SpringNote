package com.stalight.crystal.web;

import com.stalight.crystal.service.UserService;
import com.stalight.crystal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * <p>表示层</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Controller("userAction")
public class UserAction {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * <p>删除用户信息请求</p>
     */
    public void deleteUserRequest() {
        userService.deleteUserInfo();
    }
}
