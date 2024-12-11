package com.stalight.crystal.spring;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public interface ApplicationContext {

    /**
     * 根据Bean的名称获取对应的Bean对象
     * @param beanName spring配置文件中的Bean对象
     * @return 对应的单例Bean对象
     */
    Object getBean(String beanName);
}
