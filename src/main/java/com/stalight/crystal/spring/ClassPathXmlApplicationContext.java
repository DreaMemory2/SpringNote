package com.stalight.crystal.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * <p>解析spring的配置文件，然后初始化所有Bean对象</p>
     *
     * @param configLocation 配置文件的路径，注意使用：ClassPathXmlApplicationContext，配置文件应当放到类路径下
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        try {
            // 解析spring文件，然后实例化bean对象，将Bean存放到singletonObjects集合中
            // 这是dom4解析XML文件的核心对象
            SAXReader reader = new SAXReader();
            // 获取输入流，指向配置文件
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            // 读文件
            Document document = reader.read(in);
            // 获取Bean标签
            List<Node> nodes = document.selectNodes("//bean");
            nodes.forEach(node -> {
                try {
                    // 向下转型的目的是为了使用Element接口里更加丰富的方法
                    Element beanElement = (Element) node;
                    // 获取id属性
                    String id = beanElement.attributeValue("id");
                    // 获取class属性
                    String className = beanElement.attributeValue("class");
                    // 通过反射机制创建对象，将其放到Map集合中，提前曝光
                    // 获取Class
                    Class<?> clazz = Class.forName(className);
                    // 获取无参数构造方法
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    // 调用无参数构造方法实例化Bean
                    Object bean = constructor.newInstance();
                    // 将Bean曝光，加入Map集合
                    singletonObjects.put(id, bean);
                    // 记录日志
                    /*CrystalMod.LOGGER.info(singletonObjects.toString());*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            getSetter(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 再次重新把所有的Bean标签遍历一次，给对象的属性赋值
     *
     * @param nodes 节点
     */
    private void getSetter(List<Node> nodes) {
        nodes.forEach(node -> {
            try {
                Element beanElement = (Element) node;
                // 获取id
                String id = beanElement.attributeValue("id");
                // 获取className
                String className = beanElement.attributeValue("class");
                // 获取class
                Class<?> clazz = Class.forName(className);
                // 获取该Bean标签下所有的属性property标签
                List<Element> properties = beanElement.elements("property");
                // 遍历所有的属性标签
                properties.forEach(property -> {
                    try {
                        // 获取属性名
                        String name = property.attributeValue("name");
                        // 获取set方法名
                        String setterMethod = "set" + name.toUpperCase().charAt(0) + name.substring(1);
                        // 获取属性类型
                        Field field = clazz.getDeclaredField(name);
                        // 获取set方法
                        Method method = clazz.getDeclaredMethod(setterMethod, field.getType());
                        // 获取具体的值
                        String value = property.attributeValue("value");
                        String ref = property.attributeValue("ref");
                        // 说明：这个值为简单类型
                        // 调用set方法(set方法没有返回值)
                        // 声明只支持这这些简单类型
                        // byte short int long floats double boolean char
                        // Byte Short Integer Long Float Double Character String
                        Object container;
                        String propertyType = field.getType().getSimpleName();
                        container = getPropertyType(value, propertyType);
                        if (value != null) method.invoke(singletonObjects.get(id), container);
                        // 说明：这个值为复杂类型
                        // 调用set方法(set方法没有返回值)
                        if (ref != null) method.invoke(singletonObjects.get(id), singletonObjects.get(ref));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Object getPropertyType(String value, String propertyType) {
        return switch (propertyType) {
            case "byte" -> Byte.parseByte(value);
            case "short" -> Short.parseShort(value);
            case "int" -> Integer.parseInt(value);
            case "long" -> Long.parseLong(value);
            case "float" -> Float.parseFloat(value);
            case "double" -> Double.parseDouble(value);
            case "boolean" -> Boolean.parseBoolean(value);
            case "char" -> value.charAt(0);
            case "Byte" -> Byte.valueOf(value);
            case "Short" -> Short.valueOf(value);
            case "Integer" -> Integer.valueOf(value);
            case "Long" -> Long.valueOf(value);
            case "Float" -> Float.valueOf(value);
            case "Double" -> Double.valueOf(value);
            default -> value;
        };
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
