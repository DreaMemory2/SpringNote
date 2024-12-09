package com.stalight.crystal.factory;

import com.stalight.crystal.factory.simple.ToolFactory;

/**
 * <p>客户端操作</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class SimpleFactoryTest {
    /**
     * <p>客户端不需要关心产品细节，只负责消费</p>
     * <p>工厂类负责生产，客户端负责消费，生产者和消费者分离，这是简单工厂的作用</p>
     */
    public static void main(String[] args) {
        // 需要钻石剑
        ToolFactory.getTool("DiamondSwordItem").damageAttack();
        // 需要钻石镐
        ToolFactory.getTool("DiamondPickaxeItem").damageAttack();
        // 需要钻石斧
        ToolFactory.getTool("DiamondAxeItem").damageAttack();
    }
}
