package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;

/**
 * <p>注入空字符串、注入Null、注入特殊符号的学习类</p>
 * <p>详细配置参考如下</p>
 * @see <a href="#">set-injection.xml配置</a>
 * @version 2.0
 * @since 1.0
 * @author Crystal
 */
public class Vegetable {
    /**
     * <p>果实</p>
     */
    private String fruits;
    /**
     * <p>叶子</p>
     */
    private String leaves;
    /**
     * <p>根茎</p>
     */
    private String roots;

    public void setFruits(String fruits) {
        this.fruits = fruits;
    }

    public void setLeaves(String leaves) {
        this.leaves = leaves;
    }

    public void setRoots(String roots) {
        this.roots = roots;
    }

    public String printVegetables() {
        if (this.fruits != null) return "Vegetable={fruits='" + fruits + "'}";
        if (this.leaves != null) return "Vegetable={leaves='" + leaves + "'}";
        if (this.roots != null) return "Vegetable={roots='" + roots + "'}";
        return "不属于蔬菜类";
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "fruits='" + fruits + '\'' +
                ", leaves='" + leaves + '\'' +
                ", roots='" + roots + '\'' +
                '}';
    }

    /*--  回顾反射机制 --*/
    /**
     * @author Crystal
     * @since 2.0
     */
    public String getVegetables(String name) {
        CrystalMod.LOGGER.info(name);
        return name;
    }

    /**
     * @author Crystal
     * @since 2.0
     */
    public void getVegetables(String name, String color) {
        switch (color) {
            case "red" -> {
                if ("西红柿".equals(name)) CrystalMod.LOGGER.info(name);
            }
            case "green" -> {
                if ("菠菜".equals(name)) CrystalMod.LOGGER.info(name);
            }
            case "yellow" -> {
                if ("黄瓜".equals(name)) CrystalMod.LOGGER.info(name);
            }
            default -> CrystalMod.LOGGER.error("不知道这种颜色或者蔬菜属于那些类");
        }
    }
}
