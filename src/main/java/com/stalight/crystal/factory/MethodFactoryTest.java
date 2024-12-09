package com.stalight.crystal.factory;

import com.stalight.crystal.factory.method.AxeFactory;
import com.stalight.crystal.factory.method.PickaxeFactory;
import com.stalight.crystal.factory.method.SwordFactory;

/**
 * <p>客户端程序</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class MethodFactoryTest {
    public static void main(String[] args) {
        new SwordFactory().getToolItem().damageAttack();
        new PickaxeFactory().getToolItem().damageAttack();
        new AxeFactory().getToolItem().damageAttack();
    }
}
