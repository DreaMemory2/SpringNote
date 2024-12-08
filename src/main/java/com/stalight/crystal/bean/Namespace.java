package com.stalight.crystal.bean;

/**
 * <p>P命名空间注入</p>
 * <p>目的：简化Set配置</p>
 * <ul>
 *     <li>第一：在XML头部信息中添加p命名空间的配置信息：<br>xmlns:
 *   p="<a href="http://www.springframework.org/schema/p">http://www.springframework.org/schema/p</a>"</li>
 *     <li>第二：p命名空间注入是基于setter方法的，所以需要对应的属性提供setter方法</li>
 * </ul>
 * <p>C命名空间注入</p>
 * <p>目的：简化构造配置</p>
 * <ul>
 *     <li>需要在xml配置文件头部添加信息：xmlns:c="http://www.springframework.org/schema/c"</li>
 *     <li>第二：需要提供构造方法</li>
 * </ul>
 */
public class Namespace {
    // 简单类型
    private int width;
    private int height;
    // 复杂类型
    private Color color;

    // p命名空间注入，底层也是set注入
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Namespace{" +
                "width=" + width +
                ", height=" + height +
                ", color=" + color +
                '}';
    }
}
