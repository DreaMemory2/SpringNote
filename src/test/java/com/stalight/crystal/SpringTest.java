package com.stalight.crystal;

import com.stalight.crystal.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {
    /**
     * <ul>
     *     <li>{@link ApplicationContext}应用上下文，是Spring容器</li>
     *     <li>{@link ApplicationContext}接口实现很多类，其中一个实现类是{@link ClassPathXmlApplicationContext}</li>
     *     <li>{@link ClassPathXmlApplicationContext} 专门从类路径当中加载spring配置文件的一个上下文对象，其中configLocation：spring配置文件路径</li>
     *     <li>只要代码一执行，就启动Spring容器，解析xml文件，并且实例化所以bean对象，放入Spring容器中</li>
     * </ul>
     */
    @Test
    public void test01() {
        // 第一步：获取Spring容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("crystal/config/spring.xml");
        // 第二步：根据bean的id从Spring容器中获取对象：相当于获取Map集合中的key
        // 方法一：获取Spring配置文件中的对象，需要强制类型转换
        Object object = context.getBean("user01");
        // 方法二：获取具体对象，不需要强制类型转换
        User user = context.getBean("user01", User.class);
        CrystalMod.LOGGER.info("User：" + user);
        // FileSystemXmlApplicationContext：加载磁盘路径下的Spring配置文件
    }
}