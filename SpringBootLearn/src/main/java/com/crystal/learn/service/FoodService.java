package com.crystal.learn.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Value("${foodConfig.vegetable}")
    private String vegetable;
    @Value("${foodConfig.fruit}")
    private String fruit;
    @Value("${foodConfig.meat}")
    private String meat;
    @Value("${foodConfig.grain}")
    private String grain;

    public void getFood() {
        System.out.println(vegetable + " " + fruit + " " + meat + " " + grain);
    }
}
