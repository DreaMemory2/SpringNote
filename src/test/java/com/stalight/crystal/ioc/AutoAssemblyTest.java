package com.stalight.crystal.ioc;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.jdbc.MyDataSource;
import com.stalight.crystal.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoAssemblyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("crystal/config/ioc/auto-assembly.xml");
        UserService userService = context.getBean("userService", UserService.class);
        UserService userService01 = context.getBean("userService01", UserService.class);
        userService01.signUpVip();
        userService.deleteUserInfo();
    }

    /**
     * <p>引入外部的属性配置文件</p>
     * @see <a href="#">spring.xml</a>
     */
    @Test
    public void testData() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MyDataSource data = context.getBean("data", MyDataSource.class);
        CrystalMod.LOGGER.info(data.toString());
    }
}
