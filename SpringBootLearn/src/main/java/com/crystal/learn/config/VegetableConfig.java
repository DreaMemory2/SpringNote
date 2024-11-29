package com.crystal.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VegetableConfig {
    @Value("${vegetable.first.green}")
    private String vegetableGreen01;
    @Value("${vegetable.first.red}")
    private String vegetableRed01;
    @Value("${vegetable.second.green}")
    private String vegetableGreen02;
    @Value("${vegetable.second.red}")
    private String vegetableRed02;

    public void printInfo() {
        System.out.println(vegetableGreen01 + ", " + vegetableRed01 + ", " + vegetableGreen02 + ", " + vegetableRed02);
    }
}
