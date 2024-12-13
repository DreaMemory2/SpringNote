package com.stalight.crystal.service.impl;

import com.stalight.crystal.dao.UserDao;
import com.stalight.crystal.dao.VipDao;
import com.stalight.crystal.service.UserService;
import com.stalight.crystal.web.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>用户业务逻辑实现类，实现{@link UserService}接口</p>
 * <p>业务层</p>
 * @author Crystal
 * @version 2.0
 * @since 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * <p>业务层调用持久层</p>
     * <p>可以注入复杂类型</p>
     * <p>使用{@link Autowired @Autoeired}注解时，不需要指定任何属性，直接用即可</p>
     * <p>并且这个注解根据类型byType进行自动装配</p>
     * <p>解决以上问题：只能根据名字进行装配</p>
     * <p>需要联合@Autowired和@Qualifier使用</p>
     * @since 1.0
     */
    @Autowired
    @Qualifier("userDao")
    private UserDao user;
    @Autowired
    @Qualifier("vipDaoImpl")
    public VipDao vip;

    public UserServiceImpl() {

    }

    /**
     * <p>构造注入</p>
     */
    public UserServiceImpl(UserDao user, VipDao vip) {
        this.user = user;
        this.vip = vip;
    }

    /**
     * <p>注销用户账号信息逻辑</p>
     */
    @Override
    public void deleteUserInfo() {
        // 保存用户信息到数据库
        user.deleteUserInfo();
    }

    /**
     * <p>添加用户账号信息逻辑</p>
     */
    @Override
    public void addUserInfo() {
        user.addUserInfo();
    }

    /**
     * <p>增添用户表单数据</p>
     */
    @Override
    public void increaseForm() {
        user.increaseForm();
    }

    /**
     * <p>注册该用户账号会员方法</p>
     *
     * @param user 用户名
     */
    public void signUpVip(UserAction user) {
        this.user.addUserInfo();
        this.vip.signUpVip(user);
    }

    public void signUpVip() {
        this.user.addUserInfo();
        this.vip.addVipUserInfo();
    }

    /**
     * <p>使用set注入，必须提供set方法</p>
     * <p>至少这个方法以set单词开始</p>
     * <p>调用UserService中的set方法，需要配置property标签</p>
     * <p>&lt;property name="userDao" ref="userDao" /&gt;</p>
     * <ul>
     *     <li>name属性: 指定set方法的方法名;</li>
     *     <li>ref属性: 引用(references)指定的是要注入bean的id即可</li>
     * </ul>
     * <p>Set注入，Spring容器调用Set方法该UserDao赋值</p>
     *
     * @param user 通过Spring容器调用Set方法该UserDao赋值
     */
    public void setUserDao(UserDao user) {
        this.user = user;
    }

    public void setVip(VipDao vip) {
        this.vip = vip;
    }
}
