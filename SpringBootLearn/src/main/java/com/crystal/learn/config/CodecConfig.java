package com.crystal.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>纳入IOC容器管理</p>
 */
@Component
public class CodecConfig {

    /**
     * <p>在Springboot当申，这个{@link Value}注解可以将配置文件中的数据绑定到某个ava对象的属性上</p>
     */
    @Value("${codec.path}")
    private String codec;

    public void decode() {
        System.out.println(codec);
    }
}
