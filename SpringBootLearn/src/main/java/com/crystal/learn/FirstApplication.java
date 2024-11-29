package com.crystal.learn;

import com.crystal.learn.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * <p>FirstApplication是主入口应用程序，并且使用{@link SpringBootApplication}注解</p>
 * <p>而{@link SpringBootApplication}注解是复合注解，且具有三个注解的功能，分别是</p>
 * <ul>
 *     <li>{@link SpringBootConfiguration} 主入口类配置注解</li>
 *     <li>{@link EnableAutoConfiguration} 启用自动配置</li>
 *     <li>{@link ComponentScan} 负责组件扫描</li>
 * </ul>
 * <p>{@link SpringBootConfiguration}: 因为注解被{@link Configuration}注解标注，<br>
 * 所以FirstApplication主入口类同时又是一个配置类</p>
 * <p>{@link EnableAutoConfiguration}: 默认情况下，SpringBoot应用都会默认启用自动配置。<br>
 * 作用: SpringBoot应用会去类路径当中查找class，根据类路径当中的类来自动管理bean</p>
 * <p>{@link ComponentScan}: 代替xml配置(<context:component-scan base-packages="" />)。<br>
 * 扫描com.crystal.learn包下所有类名</p>
 * @since 1.0.0
 * @author Crystal
 */
@SpringBootApplication
public class FirstApplication {
    /**
     * <p>启动main方法，则启动Spring服务器</p>
     * <p> SpringApplication.run()方法: </p>
     * <p>1. 第一个参数: 输入配置类(对应配置文件)。自从配置类开始，加载所有的bean方法，而SpringApplication.class被成为起源</p>
     * @param args 接收命令行参数的
     */
    public static void main(String[] args) {
        // 运行Spring应用程序
        // ConfigurableApplicationContext继承ApplicationContext，因此run方法的返回值就是Spring容器
        ConfigurableApplicationContext context = SpringApplication.run(FirstApplication.class, args);

        // 通过Bean的name获取bean
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.findUser());

        // 关闭容器
        context.close();
    }

    /**
     * {@return 当主入口类配置类使用 @Bean 注解标注方法时，该方法的返回值被纳入IOC容器所管理}
     */
   @Bean
   public Date getDate() {
        return new Date();
   }
}