package com.stalight.crystal.dao.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * <p>持久层</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    /**
     * 注销用户账号方法
     */
    @Override
    public void deleteUserInfo() {
        CrystalMod.LOGGER.warn("该用户账号信息成功注销");
    }

    /**
     * <p>添加用户账号信息方法</p>
     */
    @Override
    public void addUserInfo() {
        CrystalMod.LOGGER.info("该用户账号信息成功添加");
    }

    /**
     * <p>增添用户表单数据</p>
     */
    @Override
    public void increaseForm() {
        CrystalMod.LOGGER.info("该用户账号成功提交表单数据信息");
    }
}
