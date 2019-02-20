package com.egojit.cloud.common.base;

/**
 * 基础表现对象
 * @author 高露 QQ：408365330
 */
public abstract class BaseVoEntity extends Entity {
    /**
     * id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
