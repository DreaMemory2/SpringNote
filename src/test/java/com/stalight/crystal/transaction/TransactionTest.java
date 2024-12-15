package com.stalight.crystal.transaction;


import com.stalight.crystal.CrystalConfig;
import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.Account;
import com.stalight.crystal.service.AccountService;
import com.stalight.crystal.service.impl.SaveInfoService;
import com.stalight.crystal.service.impl.SelectInfoService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class TransactionTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/transaction/spring-transaction.xml");

    @Test
    public void testSpringTransaction() {
        AccountService service = CONTEXT.getBean("accountService", AccountService.class);
        try {
            service.transfer("张三", "李四", 500);
            CrystalMod.LOGGER.info("转账成功");
        } catch (Exception e) {
            CrystalMod.LOGGER.error("转账失败");
            e.printStackTrace();
        }
    }

    @Test
    public void testPropagation() {
        AccountService service = CONTEXT.getBean("accountService", AccountService.class);
        Account account = new Account(6,"act-003", 1000.0);
        service.saveInfo(account);
    }

    /* 事务隔离级别测试方法 */

    @Test
    public void testSaveInfo() {
        SaveInfoService saveInfoService = CONTEXT.getBean("saveInfoService", SaveInfoService.class);
        saveInfoService.saveAccountInfo(new Account(6, "act-006", 100.0));

    }

    @Test
    public void testSelectInfo() {
        SelectInfoService selectInfoService = CONTEXT.getBean("selectInfoService", SelectInfoService.class);
        selectInfoService.getAccountInfo("act-006");
    }

    @Test
    public void testAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CrystalConfig.class);
        SelectInfoService service = context.getBean("saveInfoService", SelectInfoService.class);
        service.getAccountInfo("张三");
    }
}
