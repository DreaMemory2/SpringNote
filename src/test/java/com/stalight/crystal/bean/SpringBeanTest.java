package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/bean/spring-scope.xml");

    /**
     * <p>1. Spring默认情况下是如何管理这个Bean？</p>
     * <p>默认情况下Bean是单例(Singleton)的，在SpringContext的时候实例化</p>
     * <p>每一次调用getBean()方法的时候，都返回那个单例的对象</p>
     * <p>2. 当将bean的scope属性设置为prototype(原型)</p>
     * <p>Bean是多例，Spring上下文初始化的时候，并不会初始化这些prototype的Bean</p>
     * <p>每一次调用getBean()方法时候，实例化该Bean对象</p>
     */
    @Test
    public void testBean() {
        for (int i = 0; i < 3; i++) {
            SpringBean springBean = CONTEXT.getBean("springBean", SpringBean.class);
            CrystalMod.LOGGER.info(springBean.toString());
        }

    }
}
