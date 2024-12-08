package com.stalight.crystal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalMod {
    public static final String MOD_ID = "crystal";
    // 获取日志记录器对象
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void initialize() {
        // 第一步：创建日志记录器对象
        // 获取SpringTest01类的日志记录器对象
        // Logger logger = LoggerFactory.getLogger(MOD_ID);
        // 第二步：记录日志，根据不同的级别来输出日志
        // logger.info("This is an info log message.");
    }
}
