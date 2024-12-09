package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.factory.simple.DiamondSwordItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.Color;
import java.util.Date;

public class BeanInstantiationTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/bean/spring-bean.xml");
    @Test
    public void testBean() {
        // 第一种实例化Bean方式：通过构造方法实例化
        User user01 = CONTEXT.getBean("user01", User.class);
        CrystalMod.LOGGER.info(user01.toString());
        // 第二种实例化Bean方式：通过简单工厂模式实例化
        User user02 = CONTEXT.getBean("user02", User.class);
        CrystalMod.LOGGER.info(user02.toString());
        // 第三种实例Bean方式：通过factory-bean实例化
        DiamondSwordItem item = CONTEXT.getBean("diamond-sword", DiamondSwordItem.class);
        item.damageAttack();
        // 通过FactoryBean接口实例化
        Color color = CONTEXT.getBean("color", Color.class);
        CrystalMod.LOGGER.info(color.toString());
        Date date = CONTEXT.getBean("date", Date.class);
        CrystalMod.LOGGER.info(date.toString());
    }
}
