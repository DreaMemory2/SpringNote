package com.stalight.crystal.bean;

import org.springframework.stereotype.Component;

/**
 * <p>如果属性名是value，则value可以省略</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Component("vehicle")
public class Vehicle {

    private String taxi;

    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "taxi='" + taxi + '\'' +
                '}';
    }
}
