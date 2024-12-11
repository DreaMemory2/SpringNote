package com.stalight.crystal.spring;

import com.stalight.crystal.CrystalMod;
import org.junit.Test;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class SpringTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("frame/spring.xml");
        Object user = context.getBean("user");
        CrystalMod.LOGGER.info(user.toString());
        Object vegetables = context.getBean("vegetable");
        CrystalMod.LOGGER.info(vegetables.toString());
    }
}
