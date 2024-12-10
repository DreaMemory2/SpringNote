package com.stalight.crystal.bean.dependency;

/**
 * <p>我的Bean类</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class MyBean {
    private String id;
    private YourBean bean;

    public MyBean() {
    }

    public MyBean(String id, YourBean bean) {
        this.id = id;
        this.bean = bean;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBean(YourBean bean) {
        this.bean = bean;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id='" + id + '\'' +
                ", bean=" + bean.getId() +
                '}';
    }
}
