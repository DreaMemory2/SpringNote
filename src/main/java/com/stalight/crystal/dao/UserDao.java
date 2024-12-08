package com.stalight.crystal.dao;

/**
 * <p>持久层</p>
 */
public interface UserDao {
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
}
