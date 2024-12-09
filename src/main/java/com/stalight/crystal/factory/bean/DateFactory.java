package com.stalight.crystal.factory.bean;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>{@link DateFactory}这个工厂Bean协助我们Spring创建这个普通的Bean: Date</p>
 */
public class DateFactory implements FactoryBean<Date> {
    private final String date;

    public DateFactory(String date) {
        this.date = date;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
