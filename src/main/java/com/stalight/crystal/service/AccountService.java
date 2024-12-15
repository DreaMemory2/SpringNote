package com.stalight.crystal.service;

import com.stalight.crystal.bean.Account;

/**
 * <p>业务接口</p>
 * <p>事务就是在这个接口下控制</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {
    /**
     * <p>转账业务方法</p>
     * @param formAccount 从这个账户转出
     * @param toAccount 转入这个账户
     * @param money 转账金额
     */
    void transfer(String formAccount, String toAccount, double money);

    /**
     * <p>保存账户信息</p>
     * @param account 账户
     */
    void saveInfo(Account account);
}
