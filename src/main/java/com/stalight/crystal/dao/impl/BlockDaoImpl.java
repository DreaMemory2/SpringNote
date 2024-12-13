package com.stalight.crystal.dao.impl;

import com.stalight.crystal.CrystalMod;
import com.stalight.crystal.dao.BlockDao;
import org.springframework.stereotype.Repository;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Repository
public class BlockDaoImpl implements BlockDao {
    @Override
    public void createBlock() {
        CrystalMod.LOGGER.info("Block[crystal.block.example_block]: 成功注册");
    }
}
