package com.stalight.crystal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>编写一个类，代替Spring框架的配置文件</p>
 * <p>全注解式开发就是不在spring.xml配置文件了。写一个配置类来代替配置文件</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Configuration
@ComponentScan({
        "com.stalight.crystal.bean",
        "com.stalight.crystal.dao",
        "com.stalight.crystal.service",
        "com.stalight.crystal.aop"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CrystalConfig {

}
