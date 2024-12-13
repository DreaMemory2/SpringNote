package com.stalight.crystal.service.impl;

import com.stalight.crystal.dao.BlockDao;
import com.stalight.crystal.dao.UserDao;
import com.stalight.crystal.service.BlockService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Service
public class BlockServiceImpl implements BlockService {

    @Resource(name = "blockDaoImpl")
    private BlockDao blockDao;

    /**
     * <p>未指定name时，使用属性名作为name</p>
     * <p>如果找不到name，则根据byType类型进行装配</p>
     */
    @Resource
    private UserDao userDao;

    /**
     * <p>@Resource注解不能出现在构造方法</p>
     */
    public BlockServiceImpl() {
    }

    @Override
    public void createBlock() {
        blockDao.createBlock();
    }

    @Override
    public void useBlock() {
        userDao.addUserInfo();
    }
}
