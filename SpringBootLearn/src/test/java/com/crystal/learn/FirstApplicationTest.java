package com.crystal.learn;

import com.crystal.learn.bean.User;
import com.crystal.learn.config.CodecConfig;
import com.crystal.learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>SpringBoot项目中使用单元测试Junit，那么单元测试类必须使用这个注解进行标注</p>
 */
@SpringBootTest
public class FirstApplicationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private CodecConfig codecConfig;

    /**
     * 单元测试方法，且使用Test注解进行标注
     */
    @Test
    public void test() {
        User user = userService.findUser();
        System.out.println(user);
    }

    @Test
    public void configTest() {
        codecConfig.decode();
    }
}
