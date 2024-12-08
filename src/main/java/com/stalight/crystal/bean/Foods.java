package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;

import java.util.*;

public class Foods {
    /**
     * 注入简单类型数组输入
     */
    private String[] fruits;
    /**
     * 注入复杂类型数组输入
     */
    private Vegetable[] vegetables;
    /**
     * 注入List集合输入
     */
    private List<String> meats;
    /**
     * 注入Set集合输入
     */
    private Set<String> grain;
    /**
     * <p>注入Map集合输入</p>
     * <p>成品</p>
     */
    private Map<String, String> makes;

    public void setFruits(String[] fruits) {
        this.fruits = fruits;
    }

    public void setVegetables(Vegetable[] vegetables) {
        this.vegetables = vegetables;
    }

    public void setMeats(List<String> meats) {
        this.meats = meats;
    }

    public void setGrain(Set<String> grain) {
        this.grain = grain;
    }

    public void setMakes(Map<String, String> makes) {
        this.makes = makes;
    }

    public void printFruits() {
        CrystalMod.LOGGER.info(Arrays.toString(this.fruits));
    }

    public void printVegetables() {
        CrystalMod.LOGGER.info(Arrays.toString(vegetables));
    }

    public void printMeats() {
        CrystalMod.LOGGER.info(meats.toString());
    }

    public void printGrains() {
        CrystalMod.LOGGER.info(grain.toString());
    }

    public void printMakes() {
        CrystalMod.LOGGER.info(makes.toString());
    }
}
