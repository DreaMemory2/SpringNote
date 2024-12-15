package com.stalight.crystal.service.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.Account;
import com.stalight.crystal.dao.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>事务隔离级别</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Service
public class SelectInfoService {
    @Resource
    private AccountDao accountDao;

    /**
     * <p>负责查询select信息</p>
     * <p>当前事务可以读取到别的事务没有提交的数据</p>
     * <p>@Transactional(isolation = Isolation.READ_UNCOMMITTED)</p>
     * <p>对方事务提交之后的数据才能读取到</p>
     * <p>只读事务</p>
     * <p>启动spring的优化策略。提高select语句执行效率</p>
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public void getAccountInfo(String account) {
        Account accountInfo = accountDao.selectAccountInfo(account);
        CrystalMod.LOGGER.info("查询到账户信息: " + accountInfo.toString());
    }
}
