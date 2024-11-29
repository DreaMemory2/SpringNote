package com.crystal.learn.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>将配置绑定到对象的属性上</p>
 * @since 1.0.0
 */
@Service
public class SystemService {
    @Value("${userConfig.username}")
    private String username;
    @Value("${userConfig.email}")
    private String email;
    /**
     * 注意：当使用{@link Value}注解时，如果这个key不存在，且没有设置默认值，则报错
     */
    @Value("${userConfig.age:20}")
    private Integer age;
    @Value("${userConfig.gender:true}")
    private Boolean gender;

    public void printUserInfo() {
        System.out.println(username + "+" + email + "+" + age + "+" + (gender ? "男" : "女"));
    }
}
