package com.egojit.cloud.model.dao;

import com.egojit.cloud.common.base.BaseDbEntity;

import javax.persistence.Table;

/**
 *  菜单
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Table(name = "fk_menu")
public class Menu extends BaseDbEntity {
    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String url;

    /**
     * 父菜单
     */
    private String parentId;

    /**
     * 排序
     */
    private int sort;
    /**
     * 图标
     */
    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
