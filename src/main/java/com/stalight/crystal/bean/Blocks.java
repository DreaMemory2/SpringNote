package com.stalight.crystal.bean;

import org.springframework.stereotype.Component;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Component
public class Blocks {
    private Integer id;
    private String blocks;
    private String type;

    public Blocks() {
    }

    public Blocks(Integer id, String blocks, String type) {
        this.id = id;
        this.blocks = blocks;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
