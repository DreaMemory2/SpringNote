package com.stalight.crystal.factory.simple;

/**
 * <p>工厂类角色</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class ToolFactory {

    /**
     * <p>简单工厂模式中有一个静态方法，被称为静态工厂方法模式</p>
     * @param toolName 武器名字
     * {@return 传入工具名字就获得对应的具体工具的名字，例如：传入钻石剑就获取钻石剑}
     */
    public static ToolItem getTool(String toolName) {
        return switch (toolName) {
            case "DiamondSwordItem" -> new DiamondSwordItem();
            case "DiamondPickaxeItem" -> new DiamondPickaxeItem();
            case "DiamondAxeItem" -> new DiamondAxeItem();
            default -> throw new RuntimeException("不支持该武器的生产");
        };
    }
}
