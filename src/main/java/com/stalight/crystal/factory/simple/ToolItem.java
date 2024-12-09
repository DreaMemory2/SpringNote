package com.stalight.crystal.factory.simple;

/**
 * 抽象产品角色
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public abstract class ToolItem {
    /**
     * <p>工具都有基本的攻击伤害</p>
     */
    public abstract void damageAttack();

    /**
     * <p>工具都有基本的工具速度</p>
     */
    public abstract void damageSpeed();
}
