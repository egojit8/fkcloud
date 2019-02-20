package com.egojit.cloud.model.vo;

import com.egojit.cloud.common.base.BaseVoEntity;

import javax.validation.constraints.NotBlank;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date 2018-11-16
 */
public class LoginResultVo extends BaseVoEntity {
    /**
     * 账户
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

