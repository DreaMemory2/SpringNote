package com.crystal.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FruitConfig {
    @Value("${fruit.first.citrus}")
    private String fruitCitrus01;
    @Value("${fruit.first.apple}")
    private String fruitApple01;
    @Value("${fruit.first.grape}")
    private String fruitGrape01;
    @Value("${fruit.first.melon}")
    private String fruitMelon01;

    @Value("${fruit.second.citrus}")
    private String fruitCitrus02;
    @Value("${fruit.second.apple}")
    private String fruitApple02;
    @Value("${fruit.second.grape}")
    private String fruitGrape02;
    @Value("${fruit.second.melon}")
    private String fruitMelon02;

    public void printInfo() {
        System.out.println(fruitCitrus01 + ", " + fruitApple01 + ", " + fruitGrape01 + ", " + fruitMelon01);
        System.out.println(fruitCitrus02 + ", " + fruitApple02 + ", " + fruitGrape02 + ", " + fruitMelon02);
    }
}
