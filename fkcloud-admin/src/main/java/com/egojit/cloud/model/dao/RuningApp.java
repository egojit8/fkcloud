package com.egojit.cloud.model.dao;

import com.egojit.cloud.common.base.BaseDbEntity;

import javax.persistence.Table;

/**
 * 应用
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Table(name = "fk_runing_app")
public class RuningApp extends BaseDbEntity {

    /**
     * 镜像名称
     */
    private String imageName;

    /**
     * 应用版本例如V2.0
     */
    private String version;

    /**
     *  容器名称
     */
    private String title;

    /**
     *  镜像id
     */
    private String appId;

    /**
     * 容器id
     */
    private String containerId;

    /**
     * 容器内部端口
     */
    private Integer insidePort;

    /**
     * 容器外部端口
     */
    private Integer outsidePort;

    /**
     * 运行状态 ，0已经安装，1运行中
     */
    private Integer status;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 描述
     */
    private String remark;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public Integer getInsidePort() {
        return insidePort;
    }

    public void setInsidePort(Integer insidePort) {
        this.insidePort = insidePort;
    }

    public Integer getOutsidePort() {
        return outsidePort;
    }

    public void setOutsidePort(Integer outsidePort) {
        this.outsidePort = outsidePort;
    }

    public Integer getStatus() {
        return status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
