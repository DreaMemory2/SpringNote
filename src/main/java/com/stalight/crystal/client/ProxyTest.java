package com.stalight.crystal.client;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.proxy.OrderServiceProxy;
import com.stalight.crystal.proxy.service.OrderService;
import com.stalight.crystal.proxy.service.impl.OrderFormImpl;
import com.stalight.crystal.proxy.service.impl.OrderServiceImpl;

/**
 * <p>要统计业务接口中的每个业务方法的耗时</p>
 * <p>解决方案一：在每个义务接口中的每个方法加上统计耗时业务</p>
 * <p>缺点：违背OCP原则，相同代码没有复用</p>
 * <p>解决方案二：编写业务类的子类，让子类继承业务类，对每个业务方法进行重写</p>
 * <p>缺点：相同代码没有复用，虽然解决OCP原则，但是这种方式导致耦合度很高，因为采用继承关系(不建议使用继承)</p>
 * <p>解决方案三：</p>
 * <p>优点一：解决OCP问题，降低耦合度</p>
 *
 * <p>目前我们使用静态代理，这个静态代理的缺点</p>
 * <p>类爆炸：假设系统中有1000个接口，那么每个接口都需要对应代理类，这样类会急剧膨胀，不好维护</p>
 * <p>解决方式：动态代理(代理模式，字节码生成技术，在内存中生成class字节码，这个类就是代理类)</p>
 * <p>在内存中的动态生成字节码代理类的技术叫做：动态代理</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        OrderService service = new OrderServiceImpl();
        // 生成订单
        service.generate();
        // 修改订单
        service.modify();
        // 查看订单
        service.detail();

        // 解决方法一
        CrystalMod.LOGGER.info("解决方法一");

        // 解决方法二
        CrystalMod.LOGGER.info("解决方法二");
        // 要统计业务接口中的每个业务方法的耗时
        OrderFormImpl orderForm = new OrderFormImpl();
        orderForm.generate();
        orderForm.modify();
        orderForm.detail();

        // 解决方法三
        CrystalMod.LOGGER.info("解决方法三");
        // 创建目标对象
        OrderService target = new OrderServiceImpl();
        // 创建代理对象
        OrderService proxy = new OrderServiceProxy(target);
        // 调用代理对象的代理方法
        proxy.generate();
        proxy.modify();
        proxy.detail();
    }
}
