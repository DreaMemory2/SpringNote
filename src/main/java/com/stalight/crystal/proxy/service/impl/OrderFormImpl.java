package com.stalight.crystal.proxy.service.impl;

import com.stalight.crystal.CrystalMod;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class OrderFormImpl extends OrderServiceImpl {

    @Override
    public void generate() {
        long begin = System.currentTimeMillis();
        super.generate();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }

    @Override
    public void modify() {
        long begin = System.currentTimeMillis();
        super.modify();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }

    @Override
    public void detail() {
        long begin = System.currentTimeMillis();
        super.detail();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }
}
