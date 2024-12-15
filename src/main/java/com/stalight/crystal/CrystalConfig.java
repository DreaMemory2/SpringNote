package com.stalight.crystal;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <p>编写一个类，代替Spring框架的配置文件</p>
 * <p>全注解式开发就是不在spring.xml配置文件了。写一个配置类来代替配置文件</p>
 * <p>{@link ComponentScan}组件扫描</p>
 * <p>{@link EnableAspectJAutoProxy} 开启自动代理</p>
 * <p>{@link EnableTransactionManagement} 开启事务注解</p>
 * <p></p>
 * @author Crystal
 * @version 3.0
 * @since 1.0
 */
@Configuration
@ComponentScan({
        "com.stalight.crystal.bean",
        "com.stalight.crystal.dao",
        "com.stalight.crystal.service",
        "com.stalight.crystal.aop"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement

public class CrystalConfig {

    /**
     * <p>返回的对象就是Spring容器当中的一个Bean，并且这个Bean的名字是：dataSource</p>
     * {@return Spring框架看到@Bean注解后,会调用这个被标注的方法,这个方法的返回值是一个java对象,
     * 这个java对象自动纳入IoC容器管理}
     */
    @Bean(name = "dataSource")
    @Nullable
    public DruidDataSource getDataSource() {
        try (DruidDataSource dataSource = new DruidDataSource()){
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/stalight");
            dataSource.setUsername("root");
            dataSource.setPassword("159357ww");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>Spring在调用这个方法的时候会自动传递一个DataSource对象</p>
     * @param dataSource 数据源
     * @return Bean对象
     */
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }
}
