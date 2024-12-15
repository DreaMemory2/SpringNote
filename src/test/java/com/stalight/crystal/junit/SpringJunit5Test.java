package com.stalight.crystal.junit;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:crystal/config/spring.xml")
public class SpringJunit5Test {

    @Autowired
    private User user;

    @Test
    public void testUser() {
        CrystalMod.LOGGER.info(user.toString());
    }
}
