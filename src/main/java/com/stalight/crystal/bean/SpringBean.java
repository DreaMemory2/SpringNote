package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import org.springframework.stereotype.Component;

/**
 * <p>bean的作用域</p>
 */
@Component("bean01")
public class SpringBean {
    public SpringBean() {
        CrystalMod.LOGGER.info("SpringBean的无参数构造方法执行");
    }
}
