package com.stalight.crystal.dao.impl;

import com.stalight.crystal.bean.Account;
import com.stalight.crystal.dao.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Resource(name = "jdbcTemplate02")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectAccountInfo(String accountInfo) {
        String sql = "select account, balance from bankuser where account = ?";
        // 使用BeanPropertyRowMapper将查询结果转换为Demo类的对象列表
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), accountInfo);
    }

    @Override
    public int update(Account account) {
        String sql = "update bankuser set balance = ? where account = ?";
        return jdbcTemplate.update(sql, account.getBalance(), account.getAccount());
    }

    @Override
    public int insert(Account account) {
        String sql = "insert into bankuser values(?, ?, ?)";
        return jdbcTemplate.update(sql, account.getId(), account.getAccount(), account.getBalance() );
    }
}
