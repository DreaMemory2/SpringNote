# 源码分析

## DefaultSingletonBeanRegistry类中的三个缓存

* org.springframework.beans.factory.support.**DefaultSingletonBeanRegistry**的类中有三个重要的缓存

1. `private final Map<String, Object> singletonObjects`
    + 一级缓存：完整的单例Bean对象，说明这个缓存中的Bean对象的属性都应经赋值了
2. `private final Map<String, Object> earlySingletonObjects`
    + 二级缓存：早期的单例Bean对象，说明这个缓存中的Bean对象的属性没有赋值
3. `private final Map<String, ObjectFactory<?>> singletonFactories`
    + 三级缓存：单例工厂对象，这个里面存储大量工厂对象，每一个单例Bean对象都会对应一个单例工厂对象
    + 这个集合中存储的是，创建该单例对象时对应的那个单例工厂对象
* 这三个缓存都是Map集合，其中Key都是存储的Bean的id