package com.stalight.crystal.service.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.service.RegistryService;
import org.springframework.stereotype.Service;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Service
public class RegistryServiceImpl implements RegistryService {
    @Override
    public void registerBlocks() {
        CrystalMod.LOGGER.info("注册方块成功!");
    }

    @Override
    public void registerItems() {
        CrystalMod.LOGGER.info("注册物品成功!");
    }

    @Override
    public void registerEntities() {
        CrystalMod.LOGGER.info("注册实体成功!");
    }
}
