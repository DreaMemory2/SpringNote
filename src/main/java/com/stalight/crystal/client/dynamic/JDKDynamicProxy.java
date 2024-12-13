package com.stalight.crystal.client.dynamic;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.proxy.service.OrderService;
import com.stalight.crystal.proxy.TimerInvocationHandler;
import com.stalight.crystal.proxy.service.impl.OrderServiceImpl;
import com.stalight.crystal.util.ProxyUtil;

import java.lang.reflect.Proxy;

/**
 * <p>客户端程序</p>
 * <p>GoF代理模式之JDK动态代理</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class JDKDynamicProxy {
    /**
     * <p>1. newProxyInstance: 新建代理对象</p>
     * <p>- 也就是说，通过这个方法可以创建代理对象</p>
     * <p>- 本质上，执行两件事情，分别是：</p>
     * <p>A.在内存中动态生成一个代理类的字节码class</p>
     * <p>B.new对象，通过内存中生成的代理类代码，实例化代理对象</p>
     * <p>2. 关于newProxyInstance()方法重要参数</p>
     * <ul>
     *     <li>第一个参数: {@link ClassLoader} loader<br>
     *     类加载器：在内存当中生成字节码文件，要执行首先加载到内存当中，加载类需要类加载器，所以需要指定类加载器，
     *     并且JDK要求，目标类的类加载器必须和代理类的加载器使用同一个</li>
     *     <li>第二个参数: {@link Class Class<?>[]} interfaces
     *     <br>代理类和目标类要实现同一个接口和同一个接口，在内存中生成代理类的时候，需要告诉它实现那些接口</li>
     *     <li>第三个参数：{@link java.lang.reflect.InvocationHandler} h
     *     <br>调用处理器: 是一个接口，在调用处理器接口中编写的就是：增强代码，所以需要写接口的是实现类,
     *     并且手动写调用处理器接口的实现类不会产生类爆炸，因为调用处理器写一次即可</li>
     * </ul>
     */
    public static void main(String[] args) {
        // 创建目标对象
        OrderService orderService = new OrderServiceImpl();
        // 创建代理对象
        /*
            第一个参数：类加载器
            第二个参数：代理类要实现的接口
            第三个参数：调用处理器
         */
        // 注意：代理对象和目标对象实现的接口一样，所以可以向下转型
        // 可以使用lambda表达式, 实现调用处理器的接口
        // 上面代码通过封装之后代码简洁
        OrderService proxy = (OrderService) ProxyUtil.newProxyInstance(orderService, new TimerInvocationHandler(orderService));
        // 代理对象的代理方法
        // 注意：调用代理对象的代理方法的时候，如果你要增强代码，则目标对象的目标方法保证执行
        proxy.generate();
        proxy.modify();
        proxy.detail();

        /* Gof代理模式之invoke方法的返回值 */
        // 获取表单信息
        String info = proxy.getFormInfo();
        CrystalMod.LOGGER.info(info);
    }
}
