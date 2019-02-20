package com.egojit.cloud.model.dao;

import com.egojit.cloud.common.base.BaseDbEntity;

import javax.persistence.Table;

/**
 * 应用
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Table(name = "fk_app")
public class App extends BaseDbEntity {

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用版本例如V2.0
     */
    private String version;

    /**
     * 标签
     */
    private String tags;

    /**
     * 图标路径
     */
    private String icon;


    /**
     * 应用大小
     */
    private Long size;

    /**
     * 执行命令
     */
    private String cmd;

    /**
     * 默认端口
     */
    private Integer defaultPort;

    /**
     * 描述
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Integer getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(Integer defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
