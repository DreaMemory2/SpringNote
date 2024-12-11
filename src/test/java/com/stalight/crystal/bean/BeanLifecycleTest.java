package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.Color;

public class BeanLifecycleTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/bean/spring-lifecycle.xml");

    /**
     * <p>细节：</p>
     * <p>Spring只对singleton的Bean进行完整的生命周期管理</p>
     * <p>如果是prototype作用域Bean, 等到客户端程序一旦获取到该Bean之后，Spring容器不再管理该对象的生命周期</p>
     */
    @Test
    public void testLifecycleFive() {
        // 第四步：使用Bean
        SpringBean bean = CONTEXT.getBean("bean01", SpringBean.class);
        CrystalMod.LOGGER.info("第六步：使用Bean：" + bean);

        // 必须，手动关闭Spring容器，才能销毁Bean
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) CONTEXT;
        context.close();
    }

    @Test
    public void testSpringBean() {
        // 手动New对象
        Color color = new Color(194, 227, 254);

        // 添加Spring管理
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("color", color);

        // 从Spring容器中获取Bean对象
        Color springColor = factory.getBean("color", Color.class);
        CrystalMod.LOGGER.info(springColor.toString());
    }
}
