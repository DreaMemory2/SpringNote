package com.stalight.crystal.proxy;

import com.stalight.crystal.CrystalMod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>专门负责计时的一个调用处理器对象</p>
 * <p>在这个调用处理器当中负责计时，编写计时相关增强代码</p>
 * <p>这个调用处理器只写一个即可</p>
 *
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
public class TimerInvocationHandler implements InvocationHandler {
    /**
     * <p>目标对象</p>
     */
    private final Object target;

    /**
     * <p>需要赋值给成员变量</p>
     *
     * @param target 目标对象
     */
    public TimerInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * <p>1. 为什么强行要求必须实现{@link InvocationHandler}接口</p>
     * <p>因为一个类实现接口必须接口中的方法</p>
     * <p>以下这个方法必须是invoke(), 因为JDK在底层调用invoke()方法的程序已经提前写好了</p>
     * <p>2. invoke()方法什么时候被调用</p>
     * <p>当代理对象调用代理方法的时候，注册在InvocationHandler调用处理器当中的invoke()方法被调用</p>
     * <p>3. invoke方法中的三个参数</p>
     *
     * <p>invoke方法执行过程中，使用method方法调用目标对象的目标方法</p>
     *
     * @param proxy  代理对象的引用，这个参数使用较少
     * @param method 目标对象上的目标方法(执行方法)
     * @param args   目标方法上的实参
     * {@return invoke方法的返回值，如果代理对象调用代理方法之后，需要结果的话，invoke方法必须将目标对象的目标方法执行结果继续返回}
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这个接口的目的为了编写增强代码
        // 调用目标上的目标方法
        // 方法的四要素: 哪个对象，哪个方法，传什么参数，返回什么值
        long begin = System.currentTimeMillis();
        Object value = method.invoke(target, args);
        long end = System.currentTimeMillis();
        CrystalMod.LOGGER.info("耗时 " + (begin - end) + " 毫秒");

        return value;
    }
}
