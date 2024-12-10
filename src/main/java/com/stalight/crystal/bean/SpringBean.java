package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

/**
 * <p>bean的作用域</p>
 * <p>Bean的生命周期的十步</p>
 * <ol>
 *     <li>*实例化Bean(调用无参数构造方法)</li>
 *     <li>*给Bean属性赋值(调用set方法)</li>
 *     <li>3.1.1: 点位1: 在“Bean处理器”before之前</li>
 *     <li>3.1 - 执行“Bean后处理器”的before方法</li>
 *     <li>3.1.2: 点位2: 在“Bean处理器”before之后</li>
 *     <li>*初始化bean(调用Bean的init的方法)</li>
 *     <li>3.2 - 执行"Bean后处理器"的after方法</li>
 *     <li>*使用bean</li>
 *     <li>5.1.1: 点位3: 使用Bean之后，或者销毁Bean之前</li>
 *     <li>*销毁bean(调用Bean的destroy的方法)</li>
 * </ol>
 * <p>点位作用: 这些点位作用检查这个Bean是否实现了某个特定的接口，则Spring容器调用这些接口</p>
 * <p>点位1：检查Bean是否实现Aware接口，如果实现，则调用这个方法，传递一些数据</p>
 * <p>点位2: 检擦Bean是否实现initializingBean接口，如果实现，则调用这个方法</p>
 * <p>点位3: 检查是否实现DisposableBean接口，如果实现，则调用这个方法</p>
 * <p>细节：</p>
 * <p>Spring只对singleton的Bean进行完整的生命周期管理</p>
 * <p>如果是prototype作用域Bean, 等到客户端程序一旦获取到该Bean之后，Spring容器不再管理该对象的生命周期</p>
 * @version 2.0
 * @since 1.0
 */
@Component("bean01")
public class SpringBean implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, DisposableBean {
    public String bean;

    /**
     * <p>调用无参数构造方法</p>
     * @since 1.0
     */
    public SpringBean() {
        CrystalMod.LOGGER.info("第一步：SpringBean的无参数构造方法执行");
    }

    public void setBean(String bean) {
        this.bean = bean;
        CrystalMod.LOGGER.info("第二步：给Bean的属性赋值");
    }

    // 第六步在test测试文件里

    /**
     * <p>初始化Bean</p>
     */
    public void init() {
        // 第三步：添加初始化之前
        CrystalMod.LOGGER.info("第四步：初始化Bean");
        // 第五步：添加初始化之后
    }

    /**
     * <p>销毁Bean</p>
     */
    public void destroyBean() {
        CrystalMod.LOGGER.warn("第七步：销毁Bean");
    }

    @Override
    public String toString() {
        return "SpringBean{" +
                "bean='" + bean + '\'' +
                '}';
    }

    /**
     * @param classLoader the owning class loader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        CrystalMod.LOGGER.info("点位1：Bean的类加载器: " + classLoader);
    }

    /**
     * @param beanFactory owning BeanFactory (never {@code null}).
     * The bean can immediately call methods on the factory.
     * @throws BeansException 异常
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        CrystalMod.LOGGER.info("点位1: 生产这个Bean的工厂对象是: " + beanFactory);
    }

    /**
     * @param name the name of the bean in the factory.
     * Note that this name is the actual bean name used in the factory, which may
     * differ from the originally specified name: in particular for inner bean
     * names, the actual bean name might have been made unique through appending
     * "#..." suffixes, Use the {@link org.springframework.beans.factory.BeanFactoryUtils#originalBeanName(String) BeanFactoryUtils#originalBeanName(String)}
     * method to extract the original bean name (without suffix), if desired.
     */
    @Override
    public void setBeanName(String name) {
        CrystalMod.LOGGER.info("点位1: 这个Bean的名字是: " + name);
    }

    @Override
    public void afterPropertiesSet() {
        // 检擦Bean是否实现initializingBean接口
        CrystalMod.LOGGER.info("点位2: 该方法的初始化完成! ");
    }

    public void destroy() {
        // 检查是否实现DisposableBean接口，如果实现，则调用这个方法
        CrystalMod.LOGGER.info("点位3: Bean销毁已完成!");
    }
}
