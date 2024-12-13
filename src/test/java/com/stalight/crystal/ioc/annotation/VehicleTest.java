package com.stalight.crystal.ioc.annotation;

import com.stalight.crystal.CrystalMod;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class VehicleTest {

    @Test
    public void testVehicle() throws Exception {
        // 通过反射机制读取注解
        // 获取类
        Class<?> vehicle = Class.forName("com.stalight.crystal.bean.Vehicle");
        // 判断类上面是否有注解
        if (vehicle.isAnnotationPresent(Component.class)) {
            // 获取类上的注解
            Component component = vehicle.getAnnotation(Component.class);
            // 访问注解属性
            String value = component.value();
            // 输出
            CrystalMod.LOGGER.info(value);
        }
    }

    /**
     * <p>目前只知道一个包的名字，扫描这个包下所有的类，实例化对象</p>
     * <p>当这个类有{@link Component @Component}注解的时候，实例化对象，然后放入Map集合中</p>
     * @author Crystal
     * @since 1.0
     */
    @Test
    public void testComponentScan() {
        String packageName = "com.stalight.crystal.bean";
        // 开始写扫描程序
        // . 这个正则表达式代表任意字符，因此"."必须是一个普通字符
        // 正则表达式表示: 使用 \. 表示一个普通字符
        String path = packageName.replaceAll("\\.", "/");
        // com是在类的根路径下的一个目录
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        String urlPath = Objects.requireNonNull(url).getPath();
        // 获取一个绝对路径下的所有文件
        File file = new File(urlPath);
        File[] files = file.listFiles();
        Arrays.stream(Objects.requireNonNull(files)).forEach(f -> {
            CrystalMod.LOGGER.info(f.getName());
        });
    }
}
