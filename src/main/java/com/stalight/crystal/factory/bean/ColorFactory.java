package com.stalight.crystal.factory.bean;

import org.springframework.beans.factory.FactoryBean;

import java.awt.*;

/**
 * <p>ColorFactory也是一个Bean, 称为工厂Bean</p>
 * <p>通过工厂Bean这个特殊的Bean可以获取一个普通的Bean</p>
 */
public class ColorFactory implements FactoryBean<Color> {

    /**
     * {@return 最终我们手动new对象}
     */
    @Override
    public Color getObject() {
        return new Color(194, 227, 254);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
