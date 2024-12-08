package com.stalight.crystal.bean;

/**
 * <p>注入空字符串、注入Null、注入特殊符号的学习类</p>
 * <p>详细配置参考如下</p>
 * @see <a href="#">set-injection.xml配置</a>
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

    private String printVegetables() {
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
}
