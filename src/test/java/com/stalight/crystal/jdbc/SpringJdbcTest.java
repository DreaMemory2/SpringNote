package com.stalight.crystal.jdbc;

import com.stalight.crystal.CrystalMod;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class SpringJdbcTest {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("crystal/config/jdbc/spring-jdbc.xml");
    @Test
    public void test() {
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);

        // 执行SQL语句
        // JdbcTemplate之新增
        // insert语句
        String sql = "insert into blocks(id, blocks, type) value(?, ?, ?)";
        int count = jdbcTemplate.update(sql, 3, "StoneBrick", "Building");
        CrystalMod.LOGGER.info(String.valueOf(count));
    }

    @Test
    public void testUpdate() {
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);

        // 根据id来修改某一条记录
        String sql = "update blocks set blocks = ?, type = ? where id = ?";
        int count = jdbcTemplate.update(sql, "OakLeaves", "Nature", 3);
        CrystalMod.LOGGER.info(String.valueOf(count));
    }

    @Test
    public void testDelete() {
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);

        // 根据id来删除某个一条记录
        String sql = "delete from blocks where id = ?";
        int count = jdbcTemplate.update(sql, 3);
        CrystalMod.LOGGER.info(String.valueOf(count));
    }

    @Test
    public void testQuery() {
        // JdbcTemplate之查看值
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);
        String sqlValue = "select count(1) from blocks";
        Integer countValue = jdbcTemplate.queryForObject(sqlValue, int.class);
        CrystalMod.LOGGER.info(String.valueOf(countValue));
    }

    @Test
    public void testAddInfo() {
        // JdbcTemplate之批量添加
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);
        // sql语句
        String sql = "insert blocks(id, blocks, type) value(?, ?, ?)";
        // 准备数据
        Object[] objs01 = {4, "CoalOre", "Resource"};
        Object[] objs02 = {5, "IronOre", "Resource"};
        Object[] objs03 = {6, "GoldOre", "Resource"};
        Object[] objs04 = {7, "DiamondOre", "Resource"};
        // 添加到List集合
        List<Object[]> list = new ArrayList<>();
        list.add(objs01);
        list.add(objs02);
        list.add(objs03);
        list.add(objs04);
        // 执行sql语句
        int[] counts = jdbcTemplate.batchUpdate(sql, list);
        CrystalMod.LOGGER.info(Arrays.toString(counts));
    }

    @Test
    public void testUpdateInfo() {
        // JdbcTemplate之批量添加
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);
        // sql语句
        String sql = "update blocks set blocks = ?, type = ? where id = ?";
        // 准备数据
        Object[] objs01 = {"LazuliOre", "Resource", 4};
        Object[] objs02 = {"EmeraldOre", "Resource", 5};
        // 添加到List集合
        List<Object[]> list = new ArrayList<>();
        list.add(objs01);
        list.add(objs02);
        // 执行sql语句
        int[] counts = jdbcTemplate.batchUpdate(sql, list);
        CrystalMod.LOGGER.info(Arrays.toString(counts));
    }

    @Test
    public void testDeleteInfo() {
        // JdbcTemplate之批量添加
        JdbcTemplate jdbcTemplate = CONTEXT.getBean("jdbcTemplate", JdbcTemplate.class);
        // sql语句
        String sql = "delete from blocks where id = ?";
        // 准备数据
        Object[] objs01 = {6};
        Object[] objs02 = {7};
        // 添加到List集合
        List<Object[]> list = new ArrayList<>();
        list.add(objs01);
        list.add(objs02);
        // 执行sql语句
        int[] counts = jdbcTemplate.batchUpdate(sql, list);
        CrystalMod.LOGGER.info(Arrays.toString(counts));
    }
}
