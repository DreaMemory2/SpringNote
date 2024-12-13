package com.stalight.crystal.ioc.annotation;

import com.stalight.crystal.CrystalConfig;
import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.User;
import com.stalight.crystal.bean.Vip;
import com.stalight.crystal.dao.UserDao;
import com.stalight.crystal.service.BlockService;
import com.stalight.crystal.service.UserService;
import com.stalight.crystal.web.UserAction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class IoCAnnotationTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/annotation/spring-annotation.xml");
    @Test
    public void testAnnotation() {
        UserDao userDao = CONTEXT.getBean("userDao", UserDao.class);
        // 如果@Repository没有指定value，默认为类名首字母变小写就是bean的名字
        // UserDao userDao = CONTEXT.getBean("userDaoImpl", UserDao.class);
        UserService userService = CONTEXT.getBean("userService", UserService.class);
        UserAction userAction = CONTEXT.getBean("userAction", UserAction.class);

        // 解决多个包的问题
        User user = CONTEXT.getBean("user", User.class);
        CrystalMod.LOGGER.info(user.toString());

        // 打印日志
        userDao.deleteUserInfo();
        userService.addUserInfo();
        userAction.deleteUserRequest();
    }

    @Test
    public void testAnnotation02() {
        Vip vip = CONTEXT.getBean("vip", Vip.class);
        CrystalMod.LOGGER.info(vip.toString());
    }

    @Test
    public void testAnnotation03() {
        BlockService blockService = CONTEXT.getBean("blockServiceImpl", BlockService.class);
        blockService.createBlock();
        blockService.useBlock();
    }

    /**
     * 面向全注解开发
     */
    @Test
    public void testAnnotation04() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CrystalConfig.class);
        User user = context.getBean("user", User.class);
        CrystalMod.LOGGER.info(user.toString());
    }
}
