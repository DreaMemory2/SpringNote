package com.stalight.crystal.aop;

import com.stalight.crystal.CrystalConfig;
import com.stalight.crystal.service.RegistryService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class SpringAopTest {
    public static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/aop/spring-aop.xml");

    @Test
    public void testSpringAop() {
        RegistryService service = CONTEXT.getBean("registryServiceImpl", RegistryService.class);
        service.registerBlocks();
    }

    @Test
    public void testAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CrystalConfig.class);
        RegistryService service = context.getBean("registryServiceImpl", RegistryService.class);
        service.registerBlocks();
    }
}
