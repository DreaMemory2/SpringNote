package com.stalight.crystal.factory.method;

import com.stalight.crystal.factory.simple.DiamondSwordItem;
import com.stalight.crystal.factory.simple.ToolItem;

/**
 * <p>具体工厂角色</p>
 * <p>工厂方法模式当中的：具体工厂角色</p>
 * @author Crystal
 * @version 2.0
 * @since 1.0
 */
public class SwordFactory extends ToolFactory {
    /**
     * @since 2.0
     * {@return 工厂方法模式中的具体工厂的角色中的方法是：实例方法。}
     */
    @Override
    public ToolItem getToolItem() {
        // 最终我们手动new对象
        return new DiamondSwordItem();
    }
}
