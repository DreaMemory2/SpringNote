package com.stalight.crystal.bean;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class UserProperties {
    /**
     * <p>注入属性类对象</p>
     * <p>{@link Properties}本质也是一个Map集合</p>
     * <p>{@link Properties}的父类{@link Hashtable}，{@link Hashtable}实现了{@link Map}接口</p>
     * <p>{@link Properties}的key和value只能是String类型</p>
     */
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "UserProperties{" +
                "properties=" + properties +
                '}';
    }
}
