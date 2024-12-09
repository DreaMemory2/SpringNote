package com.stalight.crystal.factory.simple;

import com.stalight.crystal.CrystalMod;

/**
 * <p>具体产品角色</p>
 * <p>钻石斧</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class DiamondAxeItem extends ToolItem {
    @Override
    public void damageAttack() {
        CrystalMod.LOGGER.info("设置钻石斧攻击伤害为9");
    }

    @Override
    public void damageSpeed() {
        CrystalMod.LOGGER.info("设置钻石斧攻击速度为0.5");
    }
}
