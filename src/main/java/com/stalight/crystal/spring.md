## Spring 笔记

1. 三层架构：表示层(web)、业务层(service)、持久层(dao)：MVC架构模式
2. OCP开闭原则
    - 概念：OCP是软件七大开发原则当中最基本的原则之一，对扩展开发，对修改关闭
    - 例子：当对系统进行功能扩展的时候，修改之前的程序，对程序进行重新调试，这种设计是失败的
3. DIP依赖倒置原则
    - 概念：面型接口编程，面向抽象编程，降低程序的耦合度，提高扩展力
    - 例子：”上“依赖”下“，只要”下“发生改变，”上“就受到牵连
4. Ioc控制反转：Inversion of Control
    - 概念：不需要采用硬编码的方式新建的对象和维护对象的关系
    - 控制反转是一种新型的设计模式，是在GoF23模式范围外。
5. Spring框架
    - Spring框架实现了控制反转Ioc这种思想
    - IoC思想的具体实现是：依赖注入(Dependency Injection)，简称DI
    - 实现DI有两种方式：
        - set注入：执行set方法给属性赋值
        - 构造方法注入：执行构造方法该属性赋值
    - 依赖注入概念
        - 依赖：A对象于B对象之间的关系
        - 注入，可以让A对象和B对象之间产生关系的手段
6. SSM三大框架
    - S：Spring：8大模块
    - S：SpringMVC
    - M：MyBatis：操作数据库
7. Spring程序的细节
    - bean标签的id属性不可以重复
    - Spring通过反射机制，调用类的无参数构造方法
    - 把创建对象保存到Map集合中
    - 配置spring文件的名字合法即可
    - Spring支持多个配置文件
    - 配置文件中配置的类可以时自定义类，也可以是JDK中的类
    - 调用getBeans方法时，指定的id必须存在，否则报错
    - 调用getBeans方法时，可以进行强类型转换，也可以指定输出具体对象
    - ApplicationContext接口的超级父接口是BeanFactory，Bean工厂能够生产对应Bean的工厂对象，BeanFactory是Ioc容器的顶极接口
    - 执行ClassPathXmlApplicationContext类，会执行构造方法加载配置文件，创建对象时会执行无参数构造方法
8. Spring对Ioc的实现
    1. 依赖注入
        - Spring通过依赖注入的方式完成Bean管理
        - Bean创建，以及Bean管理中属性的赋值(或者叫做Bean对象之间关系的维护)
    2. Set注入
        - Set注入，基于Set方法实现的，底层通过反射机制调用属性对应的Set方法给属性赋值，所以属性要求对外提供set方法
    3. 构造注入
        - 通过调用构造方法给属性赋值
9. Set注入专题
    - Set注入外部Bean和外部Bean
    - 注入简单类型
        - 基本数据类型：byte、int、double、float、boolean、char
        - 基本数据类型对应的包装类：Byte、Integer、Double、Float、Boolean、Character
        - 字符串：String
        - 枚举：Enum
        - Class类型：Class\<?>
    - 级联属性赋值(了解)
    - 注入数组
        - 当数组为简单类型
        - 当数组为复杂类型
    - 注入List集合
    - 注入Set集合
    - 注入Map集合
    - 注入Properties集合
    - 注入null和空字符串
10. Spring 命名空间注入
    - p 命名空间注入
        - 第一步：xmlns:p="http://www.springframework.org/schema/p"
        - 第二步：使用p：属性名 = "属性值"
    - c 命名空间注入
        - 第一步：xmlns:c="http://www.springframework.org/schema/c"
        - 第二步：使用c：属性名 = "属性值"
    - util 命名空间注入
        - 第一步：xmlns:util="http://www.springframework.org/schema/util"
        - 第二步：xsi:
          schemaLocation="http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
            - 第三步：
              ```xml
              <?xml version="1.0" encoding="UTF-8"?>
              <beans xmlns="http://www.springframework.org/schema/beans"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns:util="http://www.springframework.org/schema/util"
              xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                                  http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
            
              <util:properties id="prop">
                <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
                <prop key="url">jdbc:mysql://localhost:3306/spring6</prop>
                <prop key="username">root</prop>
                <prop key="password">123</prop>
              </util:properties>
              <!-- 数据源01 -->
              <bean id="data01" class="com.stalight.crystal.MyDataSource">
                <property name="properties" ref="prop" />
              </bean>
              <!-- 数据源01 -->
              <bean id="data01" class="com.stalight.crystal.MyDataSource">
                <property name="properties" ref="prop"/>
              </bean>
              ```
11. 基于XML的自动装配
    - 根据名称自动装配
    - 根据类型自动装配
12. Spring引入外部的属性配置文件
13. Bean的作用域
    - Spring默认情况下Bean是单例的，默认scope属性设置为singleton，在SpringContext初始化的时候实例化
      每次调用getBean方法的时候，都返回那个单例对象
    - 当bean的scope属性设置为prototype时，在SpringContext初始化的时候
      不会实例化bean对象，只有调用getBean方法时，实例化bean对象
    ```xml
        <bean id="bean01" class="com.stalight.crystal.bean.SpringBean" scope="prototype" />
    ```
14. 工厂模式
    - 简单工厂模式(Simple Factory)：又叫做静态工厂模式，简单工厂模式是工厂方法模式的一种特殊实现
      - 产品：抽象产品、具体产品、工厂类
    - 工厂方法模式(Factory Method)：是23种设计模式之一
    - 抽象工厂模式(Abstract Factory)：是23种设计模式之一
    - 简单工厂详解
      - 优点：客户端只负责消费，工厂类负责生产，一个负责生产，一个负责消费。生产者和消费者分离，这是简单工厂模式的作用
      - 缺点：工厂类职责过重，一旦不能正常工作，整个系统都要受到影响；当增加新产品的时候，需要修改工厂逻辑，违背了“开闭原则”
    - 工厂方法模式
      - 抽象产品
      - 具体产品
      - 抽象工厂
      - 具体工厂
      - 缺点：每次增加产品时，都需要增加一个具体产品类和具体工厂类，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。