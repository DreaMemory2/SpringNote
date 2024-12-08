package com.stalight.crystal.dao.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.dao.VipDao;
import com.stalight.crystal.web.UserAction;

public class VipDaoImpl implements VipDao {
    /**
     * @param user 用户名
     */
    @Override
    public void signUpVip(UserAction user) {
        CrystalMod.LOGGER.info("该用户名为" + user + "成功注册会员");
    }

    /**
     * <p>注册Vip用户信息</p>
     */
    @Override
    public void addVipUserInfo() {
        CrystalMod.LOGGER.info("该用户成功注册会员");
    }
}
