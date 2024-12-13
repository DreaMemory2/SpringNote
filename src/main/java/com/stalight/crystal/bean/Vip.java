package com.stalight.crystal.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>给实例化的Bean属性赋值</p>
 * <p>{@link Value @Value}注解可以使用在set的方法上，也可以使用在属性上</p>
 * <p>甚至可以使用在构造方法中形式参数上</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Component
public class Vip {
    @Value("张三")
    public String userName;
    @Value("5")
    public Integer level;
    @Value("BV1Ft4y1g7Fb")
    public String uuid;
    @Value("游戏特权")
    public String privilege;
    @Value("true")
    public boolean isSVIP;

    /**
     * <p>可以使用在构造方法中形式参数上</p>
     */
    public Vip(@Value("张三") String userName, @Value("5") Integer level, @Value("BV1Ft4y1g7Fb") String uuid, @Value("游戏特权") String privilege, @Value("true") boolean isSVIP) {
        this.userName = userName;
        this.level = level;
        this.uuid = uuid;
        this.privilege = privilege;
        this.isSVIP = isSVIP;
    }

    /* 使用@Value注解注入的话，可以用在属性上，并且可以不提供Set方法 */
    /* Setter方法的代码... */
    @Value("true")
    public void setSVIP(boolean SVIP) {
        isSVIP = SVIP;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "userName='" + userName + '\'' +
                ", level=" + level +
                ", uuid='" + uuid + '\'' +
                ", privilege='" + privilege + '\'' +
                ", isSVIP=" + isSVIP +
                '}';
    }
}
