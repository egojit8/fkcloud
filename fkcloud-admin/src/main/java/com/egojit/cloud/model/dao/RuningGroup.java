package com.egojit.cloud.model.dao;

import com.egojit.cloud.common.base.BaseDbEntity;

import javax.persistence.Table;

/**
 * 容器分组
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Table(name = "fk_runing_group")
public class RuningGroup extends BaseDbEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
