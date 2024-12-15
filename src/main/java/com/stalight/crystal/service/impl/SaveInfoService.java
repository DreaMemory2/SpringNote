package com.stalight.crystal.service.impl;

import com.stalight.crystal.bean.Account;
import com.stalight.crystal.dao.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>事务隔离级别</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Service
public class SaveInfoService {
    @Resource
    private AccountDao accountDao;

    /**
     * <p>负责保存insert信息</p>
     * <p>超时事务</p>
     * <p>timeout = 10 设置事务超时时间为10秒</p>
     * <p>表示超过10秒如果该事务中所有的DML语句还没有执行完毕，则最终结果会选择回滚</p>
     * <p>在当前事务当中，最后一条DML语句执行之前的时间。如果最后一条DML语句后面很有很多业务逻辑，这些业务代码执行的时间不被计入超时时间</p>
     * <p>异常事务</p>
     * <p>rollbackFor = NullPointerException.class 表示只要发生RuntimeException或者这个异常的子类异常都回滚</p>
     */
    @Transactional(timeout = 10, rollbackFor = NullPointerException.class)
    public void saveAccountInfo(Account account) {
        // 模拟延迟
        // 最后DML语句之前，会计入超时时间之内
        sleep(15);
        accountDao.insert(account);
        // 最后DML语句之后，不会计入超时时间之内
        sleep(30);

        // 模拟异常
        // throw new IOException();
        throw new NullPointerException();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(1000 * millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
