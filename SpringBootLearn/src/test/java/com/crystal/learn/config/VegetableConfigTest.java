package com.crystal.learn.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VegetableConfigTest {
    @Autowired
    private VegetableConfig vegetable;

    @Test
    public void test() {
        vegetable.printInfo();
    }
}
