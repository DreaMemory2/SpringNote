package com.stalight.crystal.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <p>所有的数据元都要实现Java规范：{@link DataSource}</p>
 * <p>能够给你提供Connection对象，都是数据元</p>
 * <p>可以把数据元交给Spring容器来管理</p>
 */
public class MyDataSource implements DataSource {
    // 连接数据库的信息
    private String driver;
    private String url;
    private String username;
    private String password;
    /**
     * <p>Util命令空间(配置复用)</p>
     * Properties属性类对象，这是一个Map集合，key和value都是String类型
     */
    private Properties properties;

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> clazz) {
        return false;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String printProperties() {
        return "MyDataSource{" + "properties='" + properties + "'}'";
    }
}
