![标头.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/21376908/1692002570088-3338946f-42b3-4174-8910-7e749c31e950.jpeg#averageHue=%23f9f8f8&clientId=uc5a67c34-8a0d-4&from=paste&height=78&id=u48f9f116&originHeight=78&originWidth=1400&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23158&status=done&style=shadow&taskId=u98709943-fd0b-4e51-821c-a3fc0aef219&title=&width=1400)
# 一、Spring启示录

UserDaoImplForMySQL中主要是连接MySQL数据库进行操作。如果更换到Oracle数据库上，则需要再提供一个UserDaoImplForOracle

要对以上的操作正在进行功能的扩展，添加了一个新的类UserDaoImplForOracle来应付数据库的变化，并且还会引起连锁反应。如果想要切换到Oracle数据库上，UserServiceImpl类代码就还要需要修改

## 1.1 OCP开闭原则

以上代码违背了开闭原则OCP。开闭原则是这样说的：在软件开发过程中应当对扩展开放，对修改关闭。也就是说，如果在进行功能扩展的时候，添加额外的类是没问题的，但因为功能扩展而修改之前运行正常的程序，这是忌讳的，不被允许的。因为一旦修改之前运行正常的程序，就会导致项目整体要进行全方位的重新测试。这是相当麻烦的过程。导致以上问题的主要原因是：代码和代码之间的耦合度太高。如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1663658802926-4c783887-3bd3-4a35-b32a-b2cd57d0061c.png#averageHue=%23f2f1f1&clientId=u1787aa54-d5e2-4&from=paste&height=477&id=u472be816&originHeight=477&originWidth=628&originalType=binary&ratio=1&rotation=0&showTitle=false&size=15905&status=done&style=shadow&taskId=u5eb9db1b-8bc5-44c2-95d2-99eb3a7eceb&title=&width=628)
可以很明显的看出，**上层**是依赖**下层**的。UserController依赖UserServiceImpl，而UserServiceImpl依赖UserDaoImplForMySQL，这样就会导致
**下面只要改动**，**上面必然会受牵连（跟着也会改）**，所谓牵一发而动全身。这样也就同时违背了另一个开发原则：依赖倒置原则。

## 1.2 依赖倒置原则DIP

依赖倒置原则(Dependence Inversion Principle)，简称DIP，主要倡导面向抽象编程，面向接口编程，不要面向具体编程，让**上层**不再依赖
**下层**，下面改动了，上面的代码不会受到牵连。这样可以大大降低程序的耦合度，耦合度低了，扩展力就强了，同时代码复用性也会增强。（
**软件七大开发原则都是在为解耦合服务**）

```java
public class UserServiceImpl implements UserService {
    // 接口实现具体类
    private static final UserDao USER_DAO = new UserDaoImplForOracle();
    // 更改代码
    public UserDao userDao;

    public boolean login(String username, String password) {
        User user = USER_DAO.selectUserInfo(username, password);
        return user != null ? false : true;
    }
}
```

确实已经面向接口编程了，但对象的创建是new UserDaoImplForOracle()显然并没有完全面向接口编程，还是使用到了具体的接口实现类。

完全接口编程：`public UserDao userDao;`
如果代码是这样编写的，才算是完全面向接口编程，才符合依赖倒置原则。

但是这样userDao是null，在执行的时候就会出现空指针异常呀。因此，要解决空指针异常的问题，其实就是解决两个核心的问题：

- 第一个问题：谁来负责对象的创建。**也就是说谁来：new UserDaoImplForOracle()**
- 第二个问题：谁来负责把创建的对象赋到这个属性上。**也就是说谁来将创建的对象赋给userDao属性**

Spring框架可以做到，既符合OCP开闭原则，又符合依赖倒置原则
在Spring框架中，它可以帮助我们创建对象，并且它还可以将创建的对象赋到属性上，维护对象和对象之间的关系，比如：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/21376908/1663664672011-b1f5c534-5c8b-412b-adb3-f7c60a3ab359.png#averageHue=%23f4f3f2&clientId=u1787aa54-d5e2-4&from=paste&height=271&id=u9c3095f5&originHeight=271&originWidth=901&originalType=binary&ratio=1&rotation=0&showTitle=false&size=22617&status=done&style=shadow&taskId=u2ef22be6-9e7a-40ca-97c7-ba5b3c39b0d&title=&width=901)
Spring可以new出来UserDaoImplForMySQL对象，也可以new出来UserDaoImplForOracle对象，并且还可以让new出来的dao对象和service对象产生关系（产生关系其实本质上就是给属性赋值）。
很显然，这种方式是将对象的创建权/管理权交出去了，不再使用硬编码的方式了。同时也把对象关系的管理权交出去了，也不再使用硬编码的方式了。像这种把对象的创建权交出去，把对象关系的管理权交出去，被称为控制反转。

## 1.3 控制反转IoC

控制反转（Inversion of Control，缩写为IoC），是面向对象编程中的一种设计思想，可以用来降低代码之间的耦合度，符合依赖倒置原则。
控制反转的核心是：**将对象的创建权交出去，将对象和对象之间关系的管理权交出去，由第三方容器来负责创建与维护**。
控制反转常见的实现方式：依赖注入（Dependency Injection，简称DI）
通常，依赖注入的实现又包括两种方式：

- set方法注入
- 构造方法注入

而Spring框架就是一个实现了IoC思想的框架。
IoC可以认为是一种**全新的设计模式**，但是理论和时间成熟相对较晚，并没有包含在GoF中。（GoF指的是23种设计模式）