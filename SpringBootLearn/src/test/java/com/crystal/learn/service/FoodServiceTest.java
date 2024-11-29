package com.crystal.learn.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Test
    public void test() {
        foodService.getFood();
    }
}
