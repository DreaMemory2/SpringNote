package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.dependency.MyBean;
import com.stalight.crystal.bean.dependency.YourBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class BeanDependencyTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/bean/spring-dependency.xml");
    @Test
    public void test() {
        // singleton + setter模式下的循环依赖
        MyBean myBean = CONTEXT.getBean("myBean", MyBean.class);
        YourBean yourBean = CONTEXT.getBean("yourBean", YourBean.class);
        CrystalMod.LOGGER.info(myBean.toString());
        CrystalMod.LOGGER.info(yourBean.toString());

        // prototype + setter模式下的循环依赖
        MyBean myBean02 = CONTEXT.getBean("myBean", MyBean.class);
        YourBean yourBean01 = CONTEXT.getBean("yourBean", YourBean.class);
    }
}
