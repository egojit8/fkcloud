package com.egojit.cloud.model.vo;

import com.egojit.cloud.common.base.BaseVoEntity;

import javax.validation.constraints.NotBlank;

/**
 * 登录对象
 *
 * @author 高露 QQ：408365330
 * @date 2018-11-12
 */
public class LoginVo extends BaseVoEntity {

    /**
     * 账户
     */
    @NotBlank
    private String userName;

    /**
     * 密码
     */
    @NotBlank
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
