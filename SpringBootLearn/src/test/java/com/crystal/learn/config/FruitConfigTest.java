package com.crystal.learn.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FruitConfigTest {
    @Autowired
    private FruitConfig config;

    @Test
    public void test() {
        config.printInfo();
    }
}
