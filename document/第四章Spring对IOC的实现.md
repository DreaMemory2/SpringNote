![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=d8fot&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

# 四、Spring对IoC的实现

## 4.1 IoC 控制反转

- 控制反转是一种思想。
- 控制反转是为了降低程序耦合度，提高程序扩展力，达到OCP原则，达到DIP原则。
- 控制反转，反转的是什么？
  - 将对象的创建权利交出去，交给第三方容器负责。
  - 将对象和对象之间关系的维护权交出去，交给第三方容器负责。
- 控制反转这种思想如何实现呢？
  - DI（Dependency Injection）：依赖注入

## 4.2 依赖注入

依赖注入实现了控制反转的思想。
**Spring通过依赖注入的方式来完成Bean管理的。**
**Bean管理说的是：Bean对象的创建，以及Bean对象中属性的赋值（或者叫做Bean对象之间关系的维护）。**
依赖注入：

- 依赖指的是对象和对象之间的关联关系。
- 注入指的是一种数据传递行为，通过注入行为来让对象和对象产生关系。

依赖注入常见的实现方式包括两种：

- 第一种：set注入
- 第二种：构造注入

新建模块：spring6-002-dependency-injection



### 4.2.1 set注入

set注入，基于set方法实现的，底层会通过反射机制调用属性对应的set方法然后给属性赋值。这种方式要求属性必须对外提供set方法。

```java
public class UserDao {
    public void save() {
        System.out.println("正在保存用户数据");
    }
}
```

```java
public class UserService {

    private UserDao userDao;

    // 使用set方式注入，必须提供set方法。
    // 反射机制要调用这个方法给属性赋值的。
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUserInfo() {
        userDao.save();
    }
}

```

```xml
<bean id="userDaoBean" class="com.powernode.spring6.dao.UserDao"/>

<bean id="userServiceBean" class="com.powernode.spring6.service.UserService">
    <property name="userDao" ref="userDaoBean"/>
</bean>

```

```java
public class Test {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userServiceBean", UserService.class);
        userService.save();
    }
}

```

运行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1663905094332-411b6249-dcf5-48df-830f-33335846bed8.png#averageHue=%2387765d&clientId=u8ec31592-f8c8-4&from=paste&height=114&id=u4e8408cc&originHeight=114&originWidth=551&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9964&status=done&style=shadow&taskId=u6d75e9a1-df12-4e19-a16c-f547c75632e&title=&width=551)
重点内容是，实现原理：
通过property标签获取到属性名：userDao
通过属性名推断出set方法名：setUserDao
通过反射机制调用setUserDao()方法给属性赋值
property标签的name是属性名。
property标签的ref是要注入的bean对象的id。
**通过ref属性来完成bean的装配，这是bean最简单的一种装配方式。装配指的是：创建系统组件之间关联的动作)**
**可以把set方法注释掉，再测试一下**：
通过测试得知，底层实际上调用了setUserDao()方法。所以需要确保这个方法的存在。
我们现在把属性名修改一下，但方法名还是setUserDao()；

通过测试看到程序仍然可以正常执行，说明property标签的name是：setUserDao()方法名演变得到的。演变的规律是：

- setUsername() 演变为 username
- setPassword() 演变为 password
- setUserDao() 演变为 userDao
- setUserService() 演变为 userService

另外，对于property标签来说，ref属性也可以采用标签的方式，但使用ref属性是多数的：

```xml

<bean id="userServiceBean" class="com.powernode.spring6.service.UserService">
    <property name="userDao">
        <ref bean="userDaoBean"/>
    </property>
</bean>
```

**总结：set注入的核心实现原理：通过反射机制调用set方法来给属性赋值，让两个对象之间产生关系。**



### 4.2.2 构造注入

核心原理：通过调用构造方法来给属性赋值。

```java
public class OrderDao {
    public void deleteById() {
        System.out.println("正在删除订单");
    }
}
```

```java
public class OrderService {
    private OrderDao orderDao;
    private UserDao userDao;

    // 通过反射机制调用构造方法给属性赋值
    // 如果构造方法有两个参数
    public OrderService(OrderDao orderDao, UserDao userDao) {
        this.orderDao = orderDao;
    }

    public void delete() {
        orderDao.deleteById();
        userDao.insert();
    }
}
```

```xml
<bean id="orderDaoBean" class="com.powernode.spring6.dao.OrderDao"/>

<bean id="orderServiceBean" class="com.powernode.spring6.service.OrderService">
    <!-- index="0"表示构造方法的第一个参数，将orderDaoBean对象传递给构造方法的第一个参数 -->
    <constructor-arg index="0" ref="orderDaoBean" name="orderDao"/>
    <!--第二个参数下标是1-->
    <constructor-arg index="1" ref="userDaoBean" name="userDao" />
</bean>

<bean id="userDaoBean" class="com.powernode.spring6.dao.UserDao"/>
```

```java
@Test
public void test(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        OrderService orderServiceBean=applicationContext.getBean("orderServiceBean",OrderService.class);
        orderServiceBean.delete();
}
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1664347904052-41cd13ad-f38f-41f0-949d-a339a30a24f0.png#averageHue=%23857057&clientId=ucf23e57c-c6c2-4&from=paste&height=148&id=ub399ad0a&originHeight=148&originWidth=517&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11842&status=done&style=shadow&taskId=u4538508f-3c8c-407d-854e-5906b94ae18&title=&width=517)
**可以不使用参数下标，使用参数的名字**

**可以不指定参数下标，不指定参数名字**

**配置文件中构造方法参数的类型顺序和构造方法参数的类型顺序不一致**

通过测试得知，通过构造方法注入的时候：

- 可以通过下标
- 可以通过参数名
- 也可以不指定下标和参数名，可以类型自动推断。

Spring在装配方面做的还是比较健壮的。



## 4.3 set注入专题

### 4.3.1 注入外部Bean

在之前4.2.1中使用的案例就是注入外部Bean的方式。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDaoBean" class="com.powernode.spring6.dao.UserDao"/>

    <bean id="userServiceBean" class="com.powernode.spring6.service.UserService">
        <property name="userDao" ref="userDaoBean"/>
    </bean>

</beans>
```

外部Bean的特点：bean定义到外面，在property标签中使用ref属性进行注入。通常这种方式是常用。

### 4.3.2 注入内部Bean

内部Bean的方式：在bean标签中嵌套bean标签。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userServiceBean" class="com.powernode.spring6.service.UserService">
        <property name="userDao">
            <bean class="com.powernode.spring6.dao.UserDao"/>
        </property>
    </bean>

</beans>
```

```java
@Test
public void testInnerBean(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-inner-bean.xml");
        UserService userService=applicationContext.getBean("userServiceBean",UserService.class);
        userService.save();
        }
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1664443574869-143a6d21-9b3f-4eaa-bd9c-6d9b0e9908b5.png#averageHue=%2383715a&clientId=u37420c9c-34da-4&from=paste&height=117&id=u0c96e384&originHeight=117&originWidth=480&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9774&status=done&style=shadow&taskId=uf7b96fb5-bb62-4e68-b696-58962292b60&title=&width=480)
这种方式作为了解。

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=dbFbQ&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.3 注入简单类型

我们之前在进行注入的时候，对象的属性是另一个对象。

```java
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
```

那如果对象的属性是int类型呢？

```java
public class User {

    private int age;

    public void setAge(int age) {
        this.age = age;
    }

}
```

可以通过set注入的方式给该属性赋值吗？

- 当然可以。因为只要能够调用set方法就可以给属性赋值。

**编写程序给一个User对象的age属性赋值20：**
第一步：定义User类，提供age属性，提供age属性的setter方法。

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className User
 * @since 1.0
 **/
public class User {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }
}

```

第二步：编写spring配置文件：spring-simple-type.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="userBean" class="com.powernode.spring6.beans.User">
        <!--如果像这种int类型的属性，我们称为简单类型，这种简单类型在注入的时候要使用value属性，不能使用ref-->
        <!--<property name="age" value="20"/>-->
        <property name="age">
            <value>20</value>
        </property>
    </bean>
</beans>
```

第三步：编写测试程序

```java
@Test
public void testSimpleType(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-simple-type.xml");
        User user=applicationContext.getBean("userBean",User.class);
        System.out.println(user);
        }
```

第四步：运行测试程序
![1664444974497(1).png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1664444978134-0cdbca10-4322-4e31-a2f8-a177d3ae2e75.png#averageHue=%23857561&clientId=u37420c9c-34da-4&from=paste&height=117&id=u11b5fd73&originHeight=117&originWidth=481&originalType=binary&ratio=1&rotation=0&showTitle=false&size=7836&status=done&style=shadow&taskId=uc3933259-1cdc-4781-9342-b54b31b24a2&title=&width=481)
**需要特别注意：如果给简单类型赋值，使用value属性或value标签。而不是ref。**
简单类型包括哪些呢？可以通过Spring的源码来分析一下：BeanUtils类

```java
public class BeanUtils {

    //.......

    /**
     * Check if the given type represents a "simple" property: a simple value
     * type or an array of simple value types.
     * <p>See {@link #isSimpleValueType(Class)} for the definition of <em>simple
     * value type</em>.
     * <p>Used to determine properties to check for a "simple" dependency-check.
     * @param type the type to check
     * @return whether the given type represents a "simple" property
     * @see org.springframework.beans.factory.support.RootBeanDefinition#DEPENDENCY_CHECK_SIMPLE
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#checkDependencies
     * @see #isSimpleValueType(Class)
     */
    public static boolean isSimpleProperty(Class<?> type) {
        Assert.notNull(type, "'type' must not be null");
        return isSimpleValueType(type) || (type.isArray() && isSimpleValueType(type.getComponentType()));
    }

    /**
     * Check if the given type represents a "simple" value type: a primitive or
     * primitive wrapper, an enum, a String or other CharSequence, a Number, a
     * Date, a Temporal, a URI, a URL, a Locale, or a Class.
     * <p>{@code Void} and {@code void} are not considered simple value types.
     * @param type the type to check
     * @return whether the given type represents a "simple" value type
     * @see #isSimpleProperty(Class)
     */
    public static boolean isSimpleValueType(Class<?> type) {
        return (Void.class != type && void.class != type &&
                (ClassUtils.isPrimitiveOrWrapper(type) ||
                        Enum.class.isAssignableFrom(type) ||
                        CharSequence.class.isAssignableFrom(type) ||
                        Number.class.isAssignableFrom(type) ||
                        Date.class.isAssignableFrom(type) ||
                        Temporal.class.isAssignableFrom(type) ||
                        URI.class == type ||
                        URL.class == type ||
                        Locale.class == type ||
                        Class.class == type));
    }

    //........
}
```

**通过源码分析得知，简单类型包括：**

- **基本数据类型**
- **基本数据类型对应的包装类**
- **String或其他的CharSequence子类**
- **Number子类**
- **Date子类**
- **Enum子类**
- **URI**
- **URL**
- **Temporal子类**
- **Locale**
- **Class**
- **另外还包括以上简单值类型对应的数组类型。**

**经典案例：给数据源的属性注入值：**
假设我们现在要自己手写一个数据源，我们都知道所有的数据源都要实现javax.sql.DataSource接口，并且数据源中应该有连接数据库的信息，例如：driver、url、username、password等。

```java
package com.powernode.spring6.beans;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author 动力节点
 * @version 1.0
 * @className MyDataSource
 * @since 1.0
 **/
public class MyDataSource implements DataSource {
    private String driver;
    private String url;
    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}

```

我们给driver、url、username、password四个属性分别提供了setter方法，我们可以使用spring的依赖注入完成数据源对象的创建和属性的赋值吗？看配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="com.powernode.spring6.beans.MyDataSource">
        <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/spring"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

</beans>
```

测试程序：

```java
@Test
public void testDataSource(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-datasource.xml");
        MyDataSource dataSource=applicationContext.getBean("dataSource",MyDataSource.class);
        System.out.println(dataSource);
        }
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1664445950974-57eb35af-c4ad-47c7-9a70-341d61596656.png#averageHue=%23947f67&clientId=u37420c9c-34da-4&from=paste&height=108&id=u30991232&originHeight=108&originWidth=1422&originalType=binary&ratio=1&rotation=0&showTitle=false&size=18204&status=done&style=shadow&taskId=u56059cbc-8c37-4b68-b6ed-f70b9b65480&title=&width=1422)
你学会了吗？
**接下来，我们编写一个程序，把所有的简单类型全部测试一遍：**
编写一个类A：

```java
package com.powernode.spring6.beans;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * @author 动力节点
 * @version 1.0
 * @className A
 * @since 1.0
 **/
public class A {
    private byte b;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;
    private boolean flag;
    private char c;

    private Byte b1;
    private Short s1;
    private Integer i1;
    private Long l1;
    private Float f1;
    private Double d1;
    private Boolean flag1;
    private Character c1;

    private String str;

    private Date date;

    private Season season;

    private URI uri;

    private URL url;

    private LocalDate localDate;

    private Locale locale;

    private Class clazz;

    // 生成setter方法
    // 生成toString方法
}

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="a" class="com.powernode.spring6.beans.A">
        <property name="b" value="1"/>
        <property name="s" value="1"/>
        <property name="i" value="1"/>
        <property name="l" value="1"/>
        <property name="f" value="1"/>
        <property name="d" value="1"/>
        <property name="flag" value="false"/>

        <property name="c" value="a"/>
        <property name="b1" value="2"/>
        <property name="s1" value="2"/>
        <property name="i1" value="2"/>
        <property name="l1" value="2"/>
        <property name="f1" value="2"/>
        <property name="d1" value="2"/>
        <property name="flag1" value="true"/>
        <property name="c1" value="a"/>

        <property name="str" value="zhangsan"/>
        <!--注意：value后面的日期字符串格式不能随便写，必须是Date对象toString()方法执行的结果。-->
        <!--如果想使用其他格式的日期字符串，就需要进行特殊处理了。具体怎么处理，可以看后面的课程！！！！-->
        <property name="date" value="Fri Sep 30 15:26:38 CST 2022"/>
        <property name="season" value="WINTER"/>
        <property name="uri" value="/save.do"/>
        <!--spring6之后，会自动检查url是否有效，如果无效会报错。-->
        <property name="url" value="http://www.baidu.com"/>
        <property name="localDate" value="EPOCH"/>
        <!--java.util.Locale 主要在软件的本地化时使用。它本身没有什么功能，更多的是作为一个参数辅助其他方法完成输出的本地化。-->
        <property name="locale" value="CHINESE"/>
        <property name="clazz" value="java.lang.String"/>
    </bean>
</beans>
```

编写测试程序：

```java
@Test
public void testAllSimpleType(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-all-simple-type.xml");
    A a = applicationContext.getBean("a", A.class);
    System.out.println(a);
}
```

执行结果如下：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1664524514681-c680e261-eeaf-4536-b5db-e4f28361a03d.png#averageHue=%2396846a&clientId=u42a1b96f-60c3-4&from=paste&height=120&id=uc207cea9&originHeight=120&originWidth=1561&originalType=binary&ratio=1&rotation=0&showTitle=false&size=15664&status=done&style=shadow&taskId=ucde64917-cff9-4d4c-897a-a8925574083&title=&width=1561)
**需要注意的是：**

- **如果把Date当做简单类型的话，日期字符串格式不能随便写。格式必须符合Date的toString()
  方法格式。显然这就比较鸡肋了。如果我们提供一个这样的日期字符串：2010-10-11，在这里是无法赋值给Date类型的属性的。**
- **spring6之后，当注入的是URL，那么这个url字符串是会进行有效性检测的。如果是一个存在的url，那就没问题。如果不存在则报错。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=qtWOr&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.4 级联属性赋值（了解）

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className Clazz
 * @since 1.0
 **/
public class Clazz {
    private String name;

    public Clazz() {
    }

    public Clazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className Student
 * @since 1.0
 **/
public class Student {
    private String name;
    private Clazz clazz;

    public Student() {
    }

    public Student(String name, Clazz clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="clazzBean" class="com.powernode.spring6.beans.Clazz"/>

    <bean id="student" class="com.powernode.spring6.beans.Student">
        <property name="name" value="张三"/>

        <!--要点1：以下两行配置的顺序不能颠倒-->
        <property name="clazz" ref="clazzBean"/>
        <!--要点2：clazz属性必须有getter方法-->
        <property name="clazz.name" value="高三一班"/>
    </bean>
</beans>
```

```java
@Test
public void testCascade(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cascade.xml");
    Student student = applicationContext.getBean("student", Student.class);
    System.out.println(student);
}
```

运行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665196735272-17e84eb6-8dcd-4c69-9ed7-e35c7c8ad36d.png#averageHue=%238f7e66&clientId=ufc7e21e2-2cbb-4&from=paste&height=124&id=PZQ9M&originHeight=124&originWidth=572&originalType=binary&ratio=1&rotation=0&showTitle=false&size=12335&status=done&style=shadow&taskId=ub86e8af3-b20e-4505-a208-3614f1fe2dc&title=&width=572)
**要点：**

- **在spring配置文件中，如上，注意顺序。**
- **在spring配置文件中，clazz属性必须提供getter方法。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=KUphh&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.5 注入数组

**当数组中的元素是简单类型**：

```java
package com.powernode.spring6.beans;

import java.util.Arrays;

public class Person {
    private String[] favariteFoods;

    public void setFavariteFoods(String[] favariteFoods) {
        this.favariteFoods = favariteFoods;
    }

    @Override
    public String toString() {
        return "Person{" +
                "favariteFoods=" + Arrays.toString(favariteFoods) +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="person" class="com.powernode.spring6.beans.Person">
        <property name="favariteFoods">
            <array>
                <value>鸡排</value>
                <value>汉堡</value>
                <value>鹅肝</value>
            </array>
        </property>
    </bean>
</beans>
```

```java
@Test
public void testArraySimple(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-array-simple.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```

**当数组中的元素是非简单类型：一个订单中包含多个商品。**

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className Goods
 * @since 1.0
 **/
public class Goods {
    private String name;

    public Goods() {
    }

    public Goods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

```java
package com.powernode.spring6.beans;

import java.util.Arrays;

/**
 * @author 动力节点
 * @version 1.0
 * @className Order
 * @since 1.0
 **/
public class Order {
    // 一个订单中有多个商品
    private Goods[] goods;

    public Order() {
    }

    public Order(Goods[] goods) {
        this.goods = goods;
    }

    public void setGoods(Goods[] goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "goods=" + Arrays.toString(goods) +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="goods1" class="com.powernode.spring6.beans.Goods">
        <property name="name" value="西瓜"/>
    </bean>

    <bean id="goods2" class="com.powernode.spring6.beans.Goods">
        <property name="name" value="苹果"/>
    </bean>

    <bean id="order" class="com.powernode.spring6.beans.Order">
        <property name="goods">
            <array>
                <!--这里使用ref标签即可-->
                <ref bean="goods1"/>
                <ref bean="goods2"/>
            </array>
        </property>
    </bean>

</beans>
```

测试程序：

```java
@Test
public void testArray(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-array.xml");
    Order order = applicationContext.getBean("order", Order.class);
    System.out.println(order);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665193773078-0a30da90-244d-460e-bc2c-901d99873668.png#averageHue=%238d7a63&clientId=ufc7e21e2-2cbb-4&from=paste&height=112&id=u9f40aef5&originHeight=112&originWidth=620&originalType=binary&ratio=1&rotation=0&showTitle=false&size=12496&status=done&style=shadow&taskId=u40fc5b1e-c4d1-44d5-8964-becbb878422&title=&width=620)
**要点：**

- **如果数组中是简单类型，使用value标签。**
- **如果数组中是非简单类型，使用ref标签。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=h8Mdj&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.6 注入List集合

List集合：有序可重复

```java
package com.powernode.spring6.beans;

import java.util.List;

/**
 * @author 动力节点
 * @version 1.0
 * @className People
 * @since 1.0
 **/
public class People {
    // 一个人有多个名字
    private List<String> names;

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "People{" +
                "names=" + names +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="peopleBean" class="com.powernode.spring6.beans.People">
        <property name="names">
            <list>
                <value>铁锤</value>
                <value>张三</value>
                <value>张三</value>
                <value>张三</value>
                <value>狼</value>
            </list>
        </property>
    </bean>
</beans>
```

```java
@Test
public void testCollection(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-collection.xml");
    People peopleBean = applicationContext.getBean("peopleBean", People.class);
    System.out.println(peopleBean);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665199057422-a5f94a7c-711e-4846-9d69-2caf05d11e88.png#averageHue=%238c7b63&clientId=ufc7e21e2-2cbb-4&from=paste&height=116&id=u7bb357d8&originHeight=116&originWidth=499&originalType=binary&ratio=1&rotation=0&showTitle=false&size=12244&status=done&style=shadow&taskId=ue9649c7c-07e0-4b72-8d22-38a524b1d51&title=&width=499)
**注意：注入List集合的时候使用list标签，如果List集合中是简单类型使用value标签，反之使用ref标签。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=oagnt&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.7 注入Set集合

Set集合：无序不可重复

```java
package com.powernode.spring6.beans;

import java.util.List;
import java.util.Set;

/**
 * @author 动力节点
 * @version 1.0
 * @className People
 * @since 1.0
 **/
public class People {
    // 一个人有多个电话
    private Set<String> phones;

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    //......

    @Override
    public String toString() {
        return "People{" +
                "phones=" + phones +
                ", names=" + names +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="peopleBean" class="com.powernode.spring6.beans.People">
        <property name="phones">
            <set>
                <!--非简单类型可以使用ref，简单类型使用value-->
                <value>110</value>
                <value>110</value>
                <value>120</value>
                <value>120</value>
                <value>119</value>
                <value>119</value>
            </set>
        </property>
    </bean>
</beans>
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665199868843-bc721edd-e89a-4298-b41a-7c5ac8c93530.png#averageHue=%23917f66&clientId=ufc7e21e2-2cbb-4&from=paste&height=119&id=uab47bd18&originHeight=119&originWidth=737&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14030&status=done&style=shadow&taskId=ua0f55010-1dde-4237-a389-bdb339091f0&title=&width=737)
**要点：**

- **使用<set>标签**
- **set集合中元素是简单类型的使用value标签，反之使用ref标签。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=woKpa&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.8 注入Map集合

```java
package com.powernode.spring6.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 动力节点
 * @version 1.0
 * @className People
 * @since 1.0
 **/
public class People {
    // 一个人有多个住址
    private Map<Integer, String> addrs;

    public void setAddrs(Map<Integer, String> addrs) {
        this.addrs = addrs;
    }

    //......

    @Override
    public String toString() {
        return "People{" +
                "addrs=" + addrs +
                ", phones=" + phones +
                ", names=" + names +
                '}';
    }

}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="peopleBean" class="com.powernode.spring6.beans.People">
        <property name="addrs">
            <map>
                <!--如果key不是简单类型，使用 key-ref 属性-->
                <!--如果value不是简单类型，使用 value-ref 属性-->
                <entry key="1" value="北京大兴区"/>
                <entry key="2" value="上海浦东区"/>
                <entry key="3" value="深圳宝安区"/>
            </map>
        </property>
    </bean>
</beans>
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665200352800-0b980533-2d1f-4222-8aaf-1b253cb19a39.png#averageHue=%23917c61&clientId=ufc7e21e2-2cbb-4&from=paste&height=121&id=u49b67347&originHeight=121&originWidth=1231&originalType=binary&ratio=1&rotation=0&showTitle=false&size=20036&status=done&style=shadow&taskId=u17844f89-fdf0-45fc-b532-425734c5d39&title=&width=1231)
**要点：**

- **使用<map>标签**
- **如果key是简单类型，使用 key 属性，反之使用 key-ref 属性。**
- **如果value是简单类型，使用 value 属性，反之使用 value-ref 属性。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=tfNae&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.9 注入Properties

java.util.Properties继承java.util.Hashtable，所以Properties也是一个Map集合。

```java
package com.powernode.spring6.beans;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author 动力节点
 * @version 1.0
 * @className People
 * @since 1.0
 **/
public class People {

    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    //......

    @Override
    public String toString() {
        return "People{" +
                "properties=" + properties +
                ", addrs=" + addrs +
                ", phones=" + phones +
                ", names=" + names +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="peopleBean" class="com.powernode.spring6.beans.People">
        <property name="properties">
            <props>
                <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
                <prop key="url">jdbc:mysql://localhost:3306/spring</prop>
                <prop key="username">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>
</beans>
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665201002733-ae9273da-1fb9-495c-907e-e6c8489a7ec5.png#averageHue=%23968269&clientId=ufc7e21e2-2cbb-4&from=paste&height=121&id=u0920d032&originHeight=121&originWidth=1390&originalType=binary&ratio=1&rotation=0&showTitle=false&size=17881&status=done&style=shadow&taskId=u88c0bcbc-5a5a-46c3-ba4c-0eb0c365cbb&title=&width=1390)
**要点：**

- **使用<props>标签嵌套<prop>标签完成。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=HHmRb&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.10 注入null和空字符串

注入空字符串使用：<value/> 或者 value=""
注入null使用：<null/> 或者 不为该属性赋值

- 我们先来看一下，怎么注入空字符串。
  
  ```java
  package com.powernode.spring6.beans;
  
  ```

/**

* @author 动力节点

* @version 1.0

* @className Vip

* @since 1.0
  **/
  public class Vip {
  private String email;
  public void setEmail(String email) {
  
       this.email = email;
  
  }
  @Override
  public String toString() {
  
       return "Vip{" +
               "email='" + email + '\'' +
               '}';
  
  }
  }

```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="vipBean" class="com.powernode.spring6.beans.Vip">
        <!--空串的第一种方式-->
        <!--<property name="email" value=""/>-->
        <!--空串的第二种方式-->
        <property name="email">
            <value/>
        </property>
    </bean>

</beans>
```

```java
@Test
public void testNull(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-null.xml");
        Vip vipBean=applicationContext.getBean("vipBean",Vip.class);
        System.out.println(vipBean);
        }
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665202057919-ba40e1bb-405b-455e-9719-0c56f1f9144b.png#averageHue=%2384745f&clientId=ufc7e21e2-2cbb-4&from=paste&height=100&id=u9f7695ac&originHeight=100&originWidth=456&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9234&status=done&style=shadow&taskId=uc32bf6ca-c7f5-4213-981e-ca7d0fb83c0&title=&width=456)

- 怎么注入null呢？

第一种方式：不给属性赋值

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="vipBean" class="com.powernode.spring6.beans.Vip"/>

</beans>
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665202218759-f35ed2da-e136-4ef0-92e5-8ae4ea029620.png#averageHue=%23887862&clientId=ufc7e21e2-2cbb-4&from=paste&height=116&id=ufa4ea0b9&originHeight=116&originWidth=454&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9606&status=done&style=shadow&taskId=u9ad79395-c316-45f2-86cc-f03852ce598&title=&width=454)
第二种方式：使用<null/>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="vipBean" class="com.powernode.spring6.beans.Vip">
        <property name="email">
            <null/>
        </property>
    </bean>

</beans>
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665202218759-f35ed2da-e136-4ef0-92e5-8ae4ea029620.png#averageHue=%23887862&clientId=ufc7e21e2-2cbb-4&from=paste&height=116&id=n18Ze&originHeight=116&originWidth=454&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9606&status=done&style=shadow&taskId=u9ad79395-c316-45f2-86cc-f03852ce598&title=&width=454)

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=NS9TN&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.3.11 注入的值中含有特殊符号

XML中有5个特殊字符，分别是：<、>、'、"、&
以上5个特殊符号在XML中会被特殊对待，会被当做XML语法的一部分进行解析，如果这些特殊符号直接出现在注入的字符串当中，会报错。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665209144602-479d8a8d-d79d-4da8-bb13-6a9c7e76a949.png#averageHue=%23807d54&clientId=ufc7e21e2-2cbb-4&from=paste&height=383&id=ua27034c5&originHeight=383&originWidth=904&originalType=binary&ratio=1&rotation=0&showTitle=false&size=64612&status=done&style=shadow&taskId=u25e3c1ae-9d23-4055-ae86-1eee56cf26e&title=&width=904)
解决方案包括两种：

- 第一种：特殊符号使用转义字符代替。
- 第二种：将含有特殊符号的字符串放到：<![CDATA[]]> 当中。因为放在CDATA区中的数据不会被XML文件解析器解析。

5个特殊字符对应的转义字符分别是：

| **特殊字符** | **转义字符** |
| -------- | -------- |
| >        | &gt;     |
| <        | &lt;     |
| '        | &apos;   |
| "        | &quot;   |
| &        | &amp;    |

先使用转义字符来代替：

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className Math
 * @since 1.0
 **/
public class Math {
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Math{" +
                "result='" + result + '\'' +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="mathBean" class="com.powernode.spring6.beans.Math">
        <property name="result" value="2 < 3"/>
    </bean>
</beans>
```

```java
@Test
public void testSpecial(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-special.xml");
    Math mathBean = applicationContext.getBean("mathBean", Math.class);
    System.out.println(mathBean);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665210216232-b41609a9-2ead-4179-9f0c-dd9a9a4e033e.png#averageHue=%23877761&clientId=ufc7e21e2-2cbb-4&from=paste&height=113&id=ueeed1dd1&originHeight=113&originWidth=486&originalType=binary&ratio=1&rotation=0&showTitle=false&size=10497&status=done&style=shadow&taskId=uf47e1e75-4185-454f-8c84-9be54adcdf8&title=&width=486)
我们再来使用CDATA方式：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="mathBean" class="com.powernode.spring6.beans.Math">
        <property name="result">
            <!--只能使用value标签-->
            <value><![CDATA[2 < 3]]></value>
        </property>
    </bean>

</beans>
```

**注意：使用CDATA时，不能使用value属性，只能使用value标签。**
执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665210754241-75f884a4-a77b-4918-b398-9f7be2a58873.png#averageHue=%23867560&clientId=ufc7e21e2-2cbb-4&from=paste&height=112&id=u015b970a&originHeight=112&originWidth=492&originalType=binary&ratio=1&rotation=0&showTitle=false&size=10483&status=done&style=shadow&taskId=u9d6ba99c-0427-4885-ad07-0bf77106322&title=&width=492)

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=rz77u&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

## 4.4 p命名空间注入

目的：简化配置。
使用p命名空间注入的前提条件包括两个：

- 第一：在XML头部信息中添加p命名空间的配置信息：xmlns:
  p="[http://www.springframework.org/schema/p"](http://www.springframework.org/schema/p")

- 第二：p命名空间注入是基于setter方法的，所以需要对应的属性提供setter方法。
  
  ```java
  package com.powernode.spring6.beans;
  
  ```

/**

* @author 动力节点

* @version 1.0

* @className Customer

* @since 1.0
  **/
  public class Customer {
  private String name;
  private int age;
  public void setName(String name) {
  
       this.name = name;
  
  }
  public void setAge(int age) {
  
       this.age = age;
  
  }
  @Override
  public String toString() {
  
       return "Customer{" +
               "name='" + name + '\'' +
               ", age=" + age +
               '}';
  
  }
  }

```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="customerBean" class="com.powernode.spring6.beans.Customer" p:name="zhangsan" p:age="20"/>

</beans>
```

```java
@Test
public void testP(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-p.xml");
        Customer customerBean=applicationContext.getBean("customerBean",Customer.class);
        System.out.println(customerBean);
        }
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665215638858-c5ae8aef-43ec-455d-90a3-ac3f97c92746.png#averageHue=%238d7c66&clientId=ufc7e21e2-2cbb-4&from=paste&height=118&id=u4aeacd2a&originHeight=118&originWidth=473&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11448&status=done&style=shadow&taskId=u08f3e033-d49d-44e1-b717-e751097bdec&title=&width=473)
把setter方法去掉：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665215713205-fcebda06-c4bb-486b-a2d7-6a238088625b.png#averageHue=%23352c2b&clientId=ufc7e21e2-2cbb-4&from=paste&height=220&id=uf42f4afe&originHeight=220&originWidth=1058&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19291&status=done&style=shadow&taskId=u9c7f0649-555f-48d3-816e-a105727b293&title=&width=1058)
所以p命名空间实际上是对set注入的简化。

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=QEHc4&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

## 4.5 c命名空间注入

c命名空间是简化构造方法注入的。
使用c命名空间的两个前提条件：
第一：需要在xml配置文件头部添加信息：xmlns:c="http://www.springframework.org/schema/c"
第二：需要提供构造方法。

```java
package com.powernode.spring6.beans;

/**
 * @author 动力节点
 * @version 1.0
 * @className MyTime
 * @since 1.0
 **/
public class MyTime {
    private int year;
    private int month;
    private int day;

    public MyTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--<bean id="myTimeBean" class="com.powernode.spring6.beans.MyTime" c:year="1970" c:month="1" c:day="1"/>-->

    <bean id="myTimeBean" class="com.powernode.spring6.beans.MyTime" c:_0="2008" c:_1="8" c:_2="8"/>

</beans>
```

```java
@Test
public void testC(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-c.xml");
    MyTime myTimeBean = applicationContext.getBean("myTimeBean", MyTime.class);
    System.out.println(myTimeBean);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665216171317-2dc02c42-3f3e-42f5-80e7-c578888e2fbb.png#averageHue=%2394856d&clientId=ufc7e21e2-2cbb-4&from=paste&height=118&id=ue696a583&originHeight=118&originWidth=487&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11561&status=done&style=shadow&taskId=ua699a0a9-fe16-4e30-9d2b-71d4a4b99ee&title=&width=487)
把构造方法注释掉：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665216339051-c5eecc20-6801-46dd-a331-e5b33c66c7ed.png#averageHue=%23352b2b&clientId=ufc7e21e2-2cbb-4&from=paste&height=216&id=u54a2f985&originHeight=216&originWidth=1176&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19996&status=done&style=shadow&taskId=u2436bd3d-d8e6-4a92-9b23-68bb0fcc84c&title=&width=1176)
所以，c命名空间是依靠构造方法的。
**注意：不管是p命名空间还是c命名空间，注入的时候都可以注入简单类型以及非简单类型。**

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=nXLbi&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

## 4.6 util命名空间

使用util命名空间可以让**配置复用**。
使用util命名空间的前提是：在spring配置文件头部添加配置信息。如下：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665218059794-30411b76-a22c-4339-ab60-acad8f02ab28.png#averageHue=%23312a2a&clientId=ufc7e21e2-2cbb-4&from=paste&height=212&id=udeece73c&originHeight=212&originWidth=1494&originalType=binary&ratio=1&rotation=0&showTitle=false&size=44224&status=done&style=shadow&taskId=u39d74a7a-b50e-4d8e-b74b-c63406de34f&title=&width=1494)

```java
package com.powernode.spring6.beans;

import java.util.Properties;

/**
 * @author 动力节点
 * @version 1.0
 * @className MyDataSource1
 * @since 1.0
 **/
public class MyDataSource1 {
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyDataSource1{" +
                "properties=" + properties +
                '}';
    }
}

```

```java
package com.powernode.spring6.beans;

import java.util.Properties;

/**
 * @author 动力节点
 * @version 1.0
 * @className MyDataSource2
 * @since 1.0
 **/
public class MyDataSource2 {
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyDataSource2{" +
                "properties=" + properties +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">

    <util:properties id="prop">
        <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
        <prop key="url">jdbc:mysql://localhost:3306/spring</prop>
        <prop key="username">root</prop>
        <prop key="password">123456</prop>
    </util:properties>

    <bean id="dataSource1" class="com.powernode.spring6.beans.MyDataSource1">
        <property name="properties" ref="prop"/>
    </bean>

    <bean id="dataSource2" class="com.powernode.spring6.beans.MyDataSource2">
        <property name="properties" ref="prop"/>
    </bean>
</beans>
```

```java
@Test
public void testUtil(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-util.xml");

    MyDataSource1 dataSource1 = applicationContext.getBean("dataSource1", MyDataSource1.class);
    System.out.println(dataSource1);

    MyDataSource2 dataSource2 = applicationContext.getBean("dataSource2", MyDataSource2.class);
    System.out.println(dataSource2);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665218430727-c81e399e-294e-4bb5-b98b-2c8875b0884f.png#averageHue=%23968168&clientId=ufc7e21e2-2cbb-4&from=paste&height=140&id=ud2836a05&originHeight=140&originWidth=1518&originalType=binary&ratio=1&rotation=0&showTitle=false&size=29754&status=done&style=shadow&taskId=ubb9d8e9e-3a21-4a14-8fe5-ff81df06522&title=&width=1518)

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=truN5&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

## 4.7 基于XML的自动装配

Spring还可以完成自动化的注入，自动化注入又被称为自动装配。它可以根据**名字**进行自动装配，也可以根据**类型**进行自动装配。

### 4.7.1 根据名称自动装配

```java
package com.powernode.spring6.dao;

/**
 * @author 动力节点
 * @version 1.0
 * @className UserDao
 * @since 1.0
 **/
public class UserDao {

    public void insert(){
        System.out.println("正在保存用户数据。");
    }
}

```

```java
package com.powernode.spring6.service;

import com.powernode.spring6.dao.UserDao;

/**
 * @author 动力节点
 * @version 1.0
 * @className UserService
 * @since 1.0
 **/
public class UserService {

    private UserDao aaa;

    // 这个set方法非常关键
    public void setAaa(UserDao aaa) {
        this.aaa = aaa;
    }

    public void save(){
        aaa.insert();
    }
}

```

Spring的配置文件这样配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="com.powernode.spring6.service.UserService" autowire="byName"/>

    <bean id="aaa" class="com.powernode.spring6.dao.UserDao"/>

</beans>
```

这个配置起到关键作用：

- UserService Bean中需要添加autowire="byName"，表示通过名称进行装配。

- UserService类中有一个UserDao属性，而UserDao属性的名字是aaa，**对应的set方法是setAaa()**，正好和UserDao
  Bean的id是一样的。这就是根据名称自动装配。
  
  ```java
  @Test
  public void testAutowireByName(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
    UserService userService = applicationContext.getBean("userService", UserService.class);
    userService.save();
  }
  ```
  
  执行结果：
  ![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665535913374-7031648f-fad4-4fa1-a3f1-68dcf2318bef.png#averageHue=%23f5f4f3&clientId=ubfe41891-11ea-4&from=paste&height=116&id=u09b0d555&originHeight=116&originWidth=471&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9778&status=done&style=shadow&taskId=u99de35e6-3c78-4628-b282-8fe94b88194&title=&width=471)
  我们来测试一下，byName装配是和属性名有关还是和set方法名有关系：
  
  ```java
  package com.powernode.spring6.service;
  
  ```

import com.powernode.spring6.dao.UserDao;

/**

* @author 动力节点

* @version 1.0

* @className UserService

* @since 1.0
  **/
  public class UserService {
  // 这里没修改
  private UserDao aaa;
  /*public void setAaa(UserDao aaa) {
  
       this.aaa = aaa;
  
  }*/
  // set方法名变化了
  public void setDao(UserDao aaa){
  
       this.aaa = aaa;
  
  }
  public void save(){
  
       aaa.insert();
  
  }
  }

```
在执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665536092171-afa2acd5-68c8-4289-95bd-ab8c0f88a66d.png#averageHue=%23faf7f6&clientId=ubfe41891-11ea-4&from=paste&height=246&id=u31a28635&originHeight=246&originWidth=1329&originalType=binary&ratio=1&rotation=0&showTitle=false&size=37747&status=done&style=shadow&taskId=u205c3850-03b9-4bc5-96ea-2dd028afe91&title=&width=1329)
通过测试得知，aaa属性并没有赋值成功。也就是并没有装配成功。
我们将spring配置文件修改以下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="userService" class="com.powernode.spring6.service.UserService" autowire="byName"/>
  <!--这个id修改了-->
  <bean id="dao" class="com.powernode.spring6.dao.UserDao"/>

</beans>
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665536194436-6efd0c08-72da-437e-b3ad-143cdb00834d.png#averageHue=%23f4f3f1&clientId=ubfe41891-11ea-4&from=paste&height=112&id=u30228306&originHeight=112&originWidth=462&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9786&status=done&style=shadow&taskId=uda675890-f3bf-4882-807f-6e06230e554&title=&width=462)
这说明，如果根据名称装配(byName)，底层会调用set方法进行注入。
例如：setAge() 对应的名字是age，setPassword()对应的名字是password，setEmail()对应的名字是email。

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=o5EOt&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

### 4.7.2 根据类型自动装配

```java
package com.powernode.spring6.dao;

/**
 * @author 动力节点
 * @version 1.0
 * @className AccountDao
 * @since 1.0
 **/
public class AccountDao {
    public void insert(){
        System.out.println("正在保存账户信息");
    }
}

```

```java
package com.powernode.spring6.service;

import com.powernode.spring6.dao.AccountDao;

/**
 * @author 动力节点
 * @version 1.0
 * @className AccountService
 * @since 1.0
 **/
public class AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void save(){
        accountDao.insert();
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--byType表示根据类型自动装配-->
    <bean id="accountService" class="com.powernode.spring6.service.AccountService" autowire="byType"/>

    <bean class="com.powernode.spring6.dao.AccountDao"/>

</beans>
```

```java
@Test
public void testAutowireByType(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowire.xml");
    AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
    accountService.save();
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665537096983-d3c25b4c-21e1-499f-b348-6f829bc84a48.png#averageHue=%23f4f3f2&clientId=ubfe41891-11ea-4&from=paste&height=109&id=ucf231dcd&originHeight=109&originWidth=514&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9362&status=done&style=shadow&taskId=u73dc5c4e-c505-4247-8652-02ac58e7020&title=&width=514)
我们把UserService中的set方法注释掉，再执行：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665537145356-cf979b68-e11b-4b4f-b1b4-7c20649aa199.png#averageHue=%23faf8f7&clientId=ubfe41891-11ea-4&from=paste&height=235&id=uea831f0c&originHeight=235&originWidth=1444&originalType=binary&ratio=1&rotation=0&showTitle=false&size=38307&status=done&style=shadow&taskId=u74719bf8-872a-4eb2-a90a-10c3f6943b1&title=&width=1444)
可以看到无论是byName还是byType，在装配的时候都是基于set方法的。所以set方法是必须要提供的。提供构造方法是不行的，大家可以测试一下。这里就不再赘述。
如果byType，根据类型装配时，如果配置文件中有两个类型一样的bean会出现什么问题呢？

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="accountService" class="com.powernode.spring6.service.AccountService" autowire="byType"/>

    <bean id="x" class="com.powernode.spring6.dao.AccountDao"/>
    <bean id="y" class="com.powernode.spring6.dao.AccountDao"/>

</beans>
```

执行测试程序：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665537341888-57af14a1-eeb4-4070-8713-b4368003251d.png#averageHue=%23faf7f6&clientId=ubfe41891-11ea-4&from=paste&height=254&id=uee149cb5&originHeight=254&originWidth=1583&originalType=binary&ratio=1&rotation=0&showTitle=false&size=57785&status=done&style=shadow&taskId=ud9ec74f0-3975-42b6-9535-c4c92b16535&title=&width=1583)
测试结果说明了，当byType进行自动装配的时候，配置文件中某种类型的Bean必须是唯一的，不能出现多个。

![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=I89vS&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)

## 4.8 Spring引入外部属性配置文件

我们都知道编写数据源的时候是需要连接数据库的信息的，例如：driver url username
password等信息。这些信息可以单独写到一个属性配置文件中吗，这样用户修改起来会更加的方便。当然可以。
第一步：写一个数据源类，提供相关属性。

```java
package com.powernode.spring6.beans;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author 动力节点
 * @version 1.0
 * @className MyDataSource
 * @since 1.0
 **/
public class MyDataSource implements DataSource {
    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String driver;
    private String url;
    private String username;
    private String password;

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

    //......
}

```

第二步：在类路径下新建jdbc.properties文件，并配置信息。

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/spring
username=root
password=root123
```

第三步：在spring配置文件中引入context命名空间。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

</beans>
```

第四步：在spring中配置使用jdbc.properties文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="jdbc.properties"/>

    <bean id="dataSource" class="com.powernode.spring6.beans.MyDataSource">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
    </bean>
</beans>
```

测试程序：

```java
@Test
public void testProperties(){
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-properties.xml");
    MyDataSource dataSource = applicationContext.getBean("dataSource", MyDataSource.class);
    System.out.println(dataSource);
}
```

执行结果：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1665540422630-223562fd-e97b-40fe-96e7-df2c8744e2c2.png#averageHue=%23f7f6f4&clientId=ubfe41891-11ea-4&from=paste&height=117&id=udb7c7212&originHeight=117&originWidth=1527&originalType=binary&ratio=1&rotation=0&showTitle=false&size=18541&status=done&style=shadow&taskId=u910a7d81-51e6-40d2-ab18-67b9262e8e5&title=&width=1527)
![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=tfkd9&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)
