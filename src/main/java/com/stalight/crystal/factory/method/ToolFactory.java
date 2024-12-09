package com.stalight.crystal.factory.method;

import com.stalight.crystal.factory.simple.ToolItem;

/**
 * <p>抽象工厂角色</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public abstract class ToolFactory {
    /**
     * {@return 这个方法不是静态，而是实例方法}
     */
    public abstract ToolItem getToolItem();
}
