package com.stalight.crystal.dao.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.dao.VipDao;
import com.stalight.crystal.web.UserAction;
import org.springframework.stereotype.Repository;

/**
 * <p>如果@Repository没有指定value，默认为类名首字母变小写就是bean的名字</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Repository
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
