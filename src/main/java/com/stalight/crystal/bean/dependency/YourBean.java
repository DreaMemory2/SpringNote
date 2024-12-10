package com.stalight.crystal.bean.dependency;

/**
 * <p>你的Bean类</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class YourBean {
    private String id;
    private MyBean bean;

    public YourBean() {
    }

    public YourBean(String id, MyBean bean) {
        this.id = id;
        this.bean = bean;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBean(MyBean bean) {
        this.bean = bean;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "YourBean{" +
                "id='" + id + '\'' +
                ", bean=" + bean.getId() +
                '}';
    }
}
