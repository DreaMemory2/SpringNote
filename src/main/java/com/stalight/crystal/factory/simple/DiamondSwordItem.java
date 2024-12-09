package com.stalight.crystal.factory.simple;

import com.stalight.crystal.CrystalMod;

/**
 * <p>具体产品角色</p>
 * <p>钻石剑</p>
 * <p>工厂方法模式当中的：具体角色</p>
 * @author Crystal
 * @version 2.0
 * @since 1.0
 */
public class DiamondSwordItem extends ToolItem {

    public DiamondSwordItem() {
        CrystalMod.LOGGER.info("DiamondSwordItem无参数构造方法执行");
    }

    @Override
    public void damageAttack() {
        CrystalMod.LOGGER.info("设置钻石剑攻击伤害为6");
    }

    @Override
    public void damageSpeed() {
        CrystalMod.LOGGER.info("设置钻石剑攻击速度为1.5");
    }
}
