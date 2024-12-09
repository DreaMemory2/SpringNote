package com.stalight.crystal.factory.method;

import com.stalight.crystal.factory.simple.DiamondPickaxeItem;
import com.stalight.crystal.factory.simple.ToolItem;

/**
 * <p>具体工厂角色</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class PickaxeFactory extends ToolFactory {
    @Override
    public ToolItem getToolItem() {
        return new DiamondPickaxeItem();
    }
}
