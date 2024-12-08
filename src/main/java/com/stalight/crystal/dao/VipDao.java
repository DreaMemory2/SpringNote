package com.stalight.crystal.dao;

import com.stalight.crystal.web.UserAction;

public interface VipDao {
    /** <p>注册该用户账号会员方法</p> */
    void signUpVip(UserAction user);

    /**
     * <p>添加VIP用户信息</p>
     */
    void addVipUserInfo();
}
