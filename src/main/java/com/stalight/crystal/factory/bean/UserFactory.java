package com.stalight.crystal.factory.bean;

import com.stalight.crystal.bean.User;

/**
 * <p>简单工厂模式中的工厂类角色</p>
 */
public class UserFactory {
    /**
     * <p>简单工厂模式又叫做：静态工厂方法模式</p>
     * {@return 工厂类中有一个静态方法}
     */
    public static User getUserBean() {
        // 最终我们手动new对象
        return new User();
    }
}
