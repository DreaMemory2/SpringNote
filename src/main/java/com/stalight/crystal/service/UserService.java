package com.stalight.crystal.service;

import com.stalight.crystal.web.UserAction;

/**
 * <p>业务层</p>
 */
public interface UserService {
    /**
     * <p>注销用户账号信息方法</p>
     */
    void deleteUserInfo();

    /**
     * <p>添加用户账号信息方法</p>
     */
    void addUserInfo();

    /**
     * <p>增添用户表单数据</p>
     */
    void increaseForm();

    /**
     * <p>注册该用户账号会员方法</p>
     */
    void signUpVip(UserAction user);

    /**
     * <p>注册该用户账号会员方法</p>
     */
    void signUpVip();
}
