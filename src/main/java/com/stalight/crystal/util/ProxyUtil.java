package com.stalight.crystal.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <p>GoF代理模式之JDK动态代理工具类封装</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class ProxyUtil {

    /**
     * <p>封装一个工具方法，可以通过这个方法获取代理对象</p>
     * @param target 目标类
     * @param handler 调用处理器
     */
    public static Object newProxyInstance(Object target, InvocationHandler handler) {
        // 底层调用JDK: 动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
