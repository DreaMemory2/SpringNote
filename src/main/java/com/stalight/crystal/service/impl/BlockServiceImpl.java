package com.stalight.crystal.service.impl;

import com.stalight.crystal.bean.Account;
import com.stalight.crystal.dao.AccountDao;
import com.stalight.crystal.dao.BlockDao;
import com.stalight.crystal.dao.UserDao;
import com.stalight.crystal.service.AccountService;
import com.stalight.crystal.service.BlockService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Crystal
 * @version 2.0
 * @since 1.0
 */
@Service
public class BlockServiceImpl implements BlockService, AccountService {

    @Resource(name = "blockDaoImpl")
    private BlockDao blockDao;

    /**
     * <p>未指定name时，使用属性名作为name</p>
     * <p>如果找不到name，则根据byType类型进行装配</p>
     */
    @Resource
    private UserDao userDao;
    @Resource
    private AccountDao accountDao;

    /**
     * <p>@Resource注解不能出现在构造方法</p>
     */
    public BlockServiceImpl() {
    }

    @Override
    public void createBlock() {
        blockDao.createBlock();
    }

    @Override
    public void useBlock() {
        userDao.addUserInfo();
    }

    @Override
    public void transfer(String formAccount, String toAccount, double money) {

    }

    /**
     * <p>事务传播行为测试程序</p>
     * @param account 账户
     * @author Crystal
     * @since 2.0
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveInfo(Account account) {
        accountDao.insert(account);
        // 模拟异常
    }
}
