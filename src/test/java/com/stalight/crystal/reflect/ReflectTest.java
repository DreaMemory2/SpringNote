package com.stalight.crystal.reflect;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.bean.Vegetable;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class ReflectTest {

    /**
     * <p>调用方法四个要素</p>
     * <ul>
     *     <li>第一要素：调用对象</li>
     *     <li>第二要素: 调用方法</li>
     *     <li>第三要素: 传入参数</li>
     *     <li>第四要素：方法执行结束的返回结果</li>
     * </ul>
     * <p>调用哪个对象的哪个方法，传什么参数，返回什么值</p>
     * <p>使用反射机制也遵顼四要素规范</p>
     */
    @Test
    public void test() {
        Vegetable vegetable = new Vegetable();
        vegetable.getVegetables("西红柿", "red");
        String cucumber = vegetable.getVegetables("黄瓜");
        CrystalMod.LOGGER.info(cucumber);
    }

    /**
     * 使用反射机制
     */
    @Test
    public void testReflection() throws Exception {
        // 使用反射机制怎么调用方法
        // 获取类
        Class<?> clazz = Class.forName("com.stalight.crystal.bean.Vegetable");

        // 获取方法
        Method getVegetables = clazz.getDeclaredMethod("getVegetables", String.class);

        // 调用方法
        // 四要素：调用哪个对象的哪个方法，传什么参数，返回什么值
        // obj要素: 哪个对象; getVegetables要素: 哪个方法; "菠菜"传什么参数; value参数: 返回什么值
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Object value = getVegetables.invoke(obj, "菠菜");
        CrystalMod.LOGGER.info(value.toString());
    }
}
