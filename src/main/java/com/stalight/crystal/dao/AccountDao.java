package com.stalight.crystal.dao;

import com.stalight.crystal.bean.Account;

/**
 * <p>专门负责账户信息的CRUD操作</p>
 * <p>DAO中执行语句语句，没有任何业务逻辑</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public interface AccountDao {
    /**
     * @param accountInfo 账户
     * {@return 根据账户查询账户信息}
     */
    Account selectAccountInfo(String accountInfo);

    /**
     * @param account 账户
     * {@return 更新账户信息}
     */
    int update(Account account);

    /**
     * @param account 账户
     * {@return 保存账户信息}
     */
    int insert(Account account);
}
