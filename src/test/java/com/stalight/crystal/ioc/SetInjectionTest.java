package com.stalight.crystal.ioc;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.*;
import com.stalight.crystal.jdbc.MyDataSource;
import com.stalight.crystal.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetInjectionTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/ioc/set-injection.xml");
    private static final ApplicationContext DATA = new ClassPathXmlApplicationContext("crystal/config/spring.xml");

    /**
     * <p>定义外部bean</p>
     */
    @Test
    public void testBean() {
        UserService userService = CONTEXT.getBean("userService", UserService.class);
        userService.addUserInfo();
        UserService userService2 = CONTEXT.getBean("userService2", UserService.class);
        userService2.deleteUserInfo();
    }

    /**
     * <p>简单类型</p>
     */
    @Test
    public void testSimpleType() {
        // 简单类型注入
        User user = CONTEXT.getBean("user", User.class);
        CrystalMod.LOGGER.info(user.toString());
        // 简单类型实例
        MyDataSource dataSource = DATA.getBean("dataSource", MyDataSource.class);
        CrystalMod.LOGGER.info(dataSource.toString());
    }

    @Test
    public void testArray() {
        // 简单类型测试
        Foods fruits = CONTEXT.getBean("foods", Foods.class);
        // 复杂类型测试
        Foods vegetables = CONTEXT.getBean("foods", Foods.class);
        // List集合测试
        Foods meats = CONTEXT.getBean("foods", Foods.class);
        // Set集合测试
        Foods grains = CONTEXT.getBean("foods", Foods.class);
        // Map集合测试
        Foods makes = CONTEXT.getBean("foods", Foods.class);
        // Properties集合测试 + util命名空间使用测试
        UserProperties properties = CONTEXT.getBean("userProperties", UserProperties.class);
        // 注入null、空字符串、特殊字符测试
        Vegetable vegetable = CONTEXT.getBean("vegetables", Vegetable.class);
        // 打印信息
        fruits.printFruits();
        vegetables.printVegetables();
        meats.printMeats();
        grains.printGrains();
        makes.printMakes();
        CrystalMod.LOGGER.info(properties.toString());
        CrystalMod.LOGGER.info(vegetable.toString());
    }

    /**
     * <p>P和C命名空间注入测试</p>
     */
    @Test
    public void testNamespace() {
        Namespace pNamespace = CONTEXT.getBean("pNamespace", Namespace.class);
        CrystalMod.LOGGER.info(pNamespace.toString());
    }
}
