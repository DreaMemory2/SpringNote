package com.stalight.crystal.service.impl;

import com.stalight.crystal.bean.Account;
import com.stalight.crystal.dao.AccountDao;
import com.stalight.crystal.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>事务传播行为</p>
 * <p>在service类中有a()方法和b()方法，a()方法上有事务，b()方法上也有事务，当a()方法执行过程中调用了b()方法，
 * 事务是如何传递的？合并到一个事务里？还是开启一个新的事务？这就是事务传播行为</p>
 * <p>事务传播行为在spring框架中被定义为枚举类型</p>
 * <ul>
 *     <li>REQUIRED：支持当前事务，如果不存在就新建一个(默认) 【没有就新建，有就加入】</li>
 *     <li>SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行 【有就加入，没有就不管了】</li>
 *     <li>MANDATORY：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常 【有就加入，没有就抛异常】</li>
 *     <li>REQUIRES_NEW：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起
 *     【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】</li>
 *     <li>NOT_SUPPORTED：以非事务方式运行，如果有事务存在，挂起当前事务 【不支持事务，存在就挂起】</li>
 *     <li>NEVER：以非事务方式运行，如果有事务存在，抛出异常 【不支持事务，存在就抛异常】</li>
 *     <li>NESTED：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，
 *     行为就像REQUIRED一样。【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。】</li>
 * </ul>
 * <p>事务隔离级别</p>
 * <p>事务隔离级别类似于教室A和教室B之间的那道墙，隔离级别越高表示墙体越厚。隔音效果越好</p>
 * <ul>
 *     <li>读未提交：READ_UNCOMMITTED<br>
 *     这种隔离级别，存在脏读问题，所谓的脏读(dirty read)表示能够读取到其它事务未提交的数据。</li>
 *     <li>读提交：READ_COMMITTED<br>
 *     解决了脏读问题，其它事务提交之后才能读到，但存在不可重复读问题。</li>
 *     <li>可重复读：REPEATABLE_READ<br>
 *     解决了不可重复读，可以达到可重复读效果，只要当前事务不结束，读取到的数据一直都是一样的。但存在幻读问题。</li>
 *     <li>序列化：SERIALIZABLE<br>
 *     解决了幻读问题，事务排队执行。不支持并发。</li>
 * </ul>
 * <p>事务超时</p>
 * <p>@Transactional(timeout = 10) 表示超过10秒如果该事务中所有的DML语句还没有执行完毕的话，最终结果会选择回滚</p>
 * <p>在当前事务当中，最后一条DML语句执行之前的时间。如果最后一条DML语句后面很有很多业务逻辑，这些业务代码执行的时间不被计入超时时间</p>
 * <p>只读事务</p>
 * <p>将当前事务设置为只读事务，在该事务执行过程中只允许select语句执行，delete insert update均不可执行<br>
 * 该特性的作用是：启动spring的优化策略。提高select语句执行效率</p>
 * <p>如果该事务中确实没有增删改操作，建议设置为只读事务</p>
 * <p>异常事务</p>
 * <p>rollbackFor = NullPointerException.class 表示只要发生RuntimeException或者这个异常的子类异常都回滚</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Resource(name = "blockServiceImpl")
    private AccountService accountService;
    /**
     * <p>控制事务，因为在这个方法中要完成所有的转账业务</p>
     * @param formAccount 从这个账户转出
     * @param toAccount 转入这个账户
     * @param money 转账金额
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void transfer(String formAccount, String toAccount, double money) {
        // 第一步：开启事务

        // 第二步：执行核心业务逻辑
        // 查询转账账户的余额是否充足
        Account account01 = accountDao.selectAccountInfo(formAccount);
        if (account01.getBalance() < money) throw new RuntimeException("余额不足");
        // 余额充足
        Account account02 = accountDao.selectAccountInfo(toAccount);

        // 将内存中两个对象的余额先修改
        account01.setBalance(account01.getBalance() - money);
        account02.setBalance(account02.getBalance() + money);

        // 数据库更新
        int count = accountDao.update(account01);
        // 模拟异常
        /*String value = null;*/

        count += accountDao.update(account02);

        if (count != 2) {
            throw new RuntimeException("转账失败");
        }

        // 第三步：如果执行过程中，没有异常，则提交事务

        // 第四步：如果遇到异常，回滚事务
    }

    /**
     * <p>事务传播行为测试程序</p>
     * @param account 账户
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveInfo(Account account) {
        // 这里调用dao的insert方法
        // 保存act-003账户
        accountDao.insert(account);
        // 创建账户对象，调用二号Service方法
        // 保存act-004账户
        try {
            Account account02 = new Account(7, "act-004", 1000.0);
            accountService.saveInfo(account02);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 继续一号事务处理
    }
}
