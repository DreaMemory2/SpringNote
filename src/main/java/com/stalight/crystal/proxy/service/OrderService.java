package com.stalight.crystal.proxy.service;

/**
 * <p>订单业务接口</p>
 * <p>代理对象和目标对象的公共接口</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public interface OrderService {
    /**
     * <p>生成订单</p>
     */
    void generate();

    /**
     * <p>修改订单信息</p>
     */
    void modify();

    /**
     * <p>查看订单详情</p>
     */
    void detail();

    /**
     * {@return 获取表单信息}
     */
    default String getFormInfo() {
        return "订单内容信息为空";
    }
}
