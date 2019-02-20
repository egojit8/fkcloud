package com.egojit.cloud.service;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.model.vo.LoginResultVo;
import com.egojit.cloud.model.vo.LoginVo;

/**
 * 用户服务
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface UserService {

    /**
     * 通过用户名获取账户信息
     * @param userName
     * @return
     */
    LoginResultVo getUserByName(String userName);


    /**
     * 登录
     * @param loginVo 登录参数
     * @return
     */
    BaseResult login(LoginVo loginVo);
}
