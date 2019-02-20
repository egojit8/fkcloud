package com.egojit.cloud.model.vo;

import com.egojit.cloud.common.base.BaseVoEntity;

import java.util.List;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public class RuningAppVo extends BaseVoEntity {

    /**
     * 容器名称
     */
    private String title;

    /**
     * 镜像id
     */
    private String appId;

    /**
     * 容器id
     */
    private String containerId;

    /**
     * 分组
     */
    private RuningGroupVo group;

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

    public RuningGroupVo getGroup() {
        return group;
    }

    public void setGroup(RuningGroupVo group) {
        this.group = group;
    }
}
