package com.egojit.cloud.model.dao;

import com.egojit.cloud.common.base.BaseDbEntity;

import javax.persistence.Table;

/**
 * 用户对象
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Table(name = "fk_user")
public class User extends BaseDbEntity {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String pwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
