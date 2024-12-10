package com.stalight.crystal.bean.processor;

import com.stalight.crystal.CrystalMod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>通用处理器</p>
 * <p>方法有两个参数，第一个是创建的bean的对象；第二个参数是Bean的名字</p>
 */
public class CommonProcessor implements BeanPostProcessor {
    /**
     *
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * {@return before方法}
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        CrystalMod.LOGGER.info("第三步：执行Bean后处理器的Before方法");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    /**
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * {@return after方法}
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        CrystalMod.LOGGER.info("第五步：执行Bean后处理器的After方法");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
