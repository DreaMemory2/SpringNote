package com.crystal.learn.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemServiceTest {
    @Autowired
    private SystemService systemService;

    @Test
    public void test() {
        systemService.printUserInfo();
    }
}
