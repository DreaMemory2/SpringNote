package com.stalight.crystal.junit;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:crystal/config/spring.xml")
public class SpringJunit4Test {

    @Autowired
    public User user;

    @Test
    public void testUser() {
        CrystalMod.LOGGER.info(user.toString());
    }
}
