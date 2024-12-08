package com.stalight.crystal.ioc;

import com.stalight.crystal.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/ioc/injection.xml");

    /**
     * <p>Set注入</p>
     */
    @Test
    public void setInjection() {
        UserService userService = CONTEXT.getBean("userService", UserService.class);
        // 保存用户信息
        userService.addUserInfo();
    }

    /**
     * <p>构造注入</p>
     */
    @Test
    public void constructorInjection() {
        UserService userService = CONTEXT.getBean("userService", UserService.class);
        // 删除用户信息
        userService.deleteUserInfo();
    }
}
