package com.stalight.crystal.proxy;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.proxy.service.OrderService;

/**
 * <p>代理对象</p>
 * <p>代理对象和目标对象要具有相同的行为，就要实现同一个或同一些接口</p>
 * <p>客户端在使用代理对象的时候就像在使用目标对象一样</p>
 *
 * <p>类和类之间的关系：包括6种关系</p>
 * <p>第一种：泛化关系(继承: is a)</p>
 * <p>第二种：关联关系(包含: has a)</p>
 * <p>相比来说：泛化关系的耦合度比关联关系高，优先选择使用关联关系</p>
 *
 *
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceProxy implements OrderService {

    /**
     * <p>将目标对象作为代理对象的一个属性，这种关系叫做关联关系，比继承关系的耦合度低</p>
     * <p>代理对象中含有目标对象的引用。关联关系 has a</p>
     * <p>目标对象一定实现OrderService接口</p>
     * <p>注意：这里写一个公共接口类型，因为公共接口耦合度低</p>
     */
    private final OrderService orderService;

    /**
     * <p>创建代理对象的时候，传一个目标对象给代理对象</p>
     * @param orderService 目标对象
     */
    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * <p>代理方法</p>
     */
    @Override
    public void generate() {
        // 增强代码
        long begin = System.currentTimeMillis();
        // 调用目标对象的目标方法
        orderService.generate();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }

    /**
     * <p>代理方法</p>
     */
    @Override
    public void modify() {
        // 增强代码
        long begin = System.currentTimeMillis();
        // 调用目标对象的目标方法
        orderService.modify();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }

    /**
     * <p>代理方法</p>
     */
    @Override
    public void detail() {
        // 增强代码
        long begin = System.currentTimeMillis();
        // 调用目标对象的目标方法
        orderService.detail();
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (end - begin) + " 毫秒");
    }
}
