package com.stalight.crystal.proxy.service.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.proxy.service.OrderService;

/**
 * <p>目标对象</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImpl implements OrderService {
    /**
     * <p>生成订单</p>
     * <p>目标方法</p>
     */
    @Override
    public void generate() {
        // 模拟生成订单的耗时
        sleep(1000);
        CrystalMod.LOGGER.info("订单已生成");
    }

    /**
     * <p>修改订单信息</p>
     * <p>目标方法</p>
     */
    @Override
    public void modify() {
        // 模拟修改订单的耗时
        sleep(400);
        CrystalMod.LOGGER.info("订单已修改");
    }

    /**
     * <p>查看订单详情</p>
     * <p>目标方法</p>
     */
    @Override
    public void detail() {
        // 模拟查询订单的耗时
        sleep(100);
        CrystalMod.LOGGER.info("请看订单详情");
    }

    @Override
    public String getFormInfo() {
        CrystalMod.LOGGER.info("订单已获取信息");
        return "订单成功派送";
    }

    /**
     * <p>模拟使用订单的耗时</p>
     */
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
