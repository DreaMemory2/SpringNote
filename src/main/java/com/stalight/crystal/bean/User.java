package com.stalight.crystal.bean;

import com.stalight.crystal.CrystalMod;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>单类型包括有那些呢？</p>
 * <ul>
 *     <li>基本数据类型</li>
 *     <li>基本数据类型对应的包装类</li>
 *     <li>String或其他的CharSequence子类</li>
 *     <li>Number子类</li>
 *     <li>Date子类</li>
 *     <li>Enum子类</li>
 *     <li>URI</li>
 *     <li>URL</li>
 *     <li>Temporal子类</li>
 *     <li>Locale</li>
 *     <li>Class</li>
 *     <li>另外还包括以上简单类型对应的数组类型</li>
 * </ul>
 * @version 2.0
 * @see org.springframework.util.ClassUtils#isSimpleValueType(Class) ClassUtils是否为简单类型
 */
@Component("user")
public class User {
    /**
     * <p>名字</p>
     * <p>测试简单类型-String类型</p>
     */
    private String name;
    /**
     * <p>密码</p>
     * <p>测试简单类型-Int类型</p>
     */
    private int password;
    /**
     * <p>枚举类型</p>
     */
    private Season season;
    /**
     * <p>Class类型</p>
     */
    private Class<User> clazz;
    /**
     * <p>Date类型</p>
     */
    private Date born;

    /**
     * <p>默认情况下，Spring通过反射机制，调用类的无参数构造方法</p>
     * <p>Bean的实例化第一种方式</p>
     * @since 1.0
     */
    public User() {
        CrystalMod.LOGGER.info("Spring Bean User 无参数构造方法执行");
        // Class.forName().newInstance();
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setClazz(Class<User> clazz) {
        this.clazz = clazz;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password=" + password +
                ", season=" + season +
                ", clazz=" + clazz +
                ", born=" + born +
                '}';
    }
}
