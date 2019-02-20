package com.egojit.cloud.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseServiceImpl;
import com.egojit.cloud.common.utils.PojoUtils;
import com.egojit.cloud.mapper.UserMapper;
import com.egojit.cloud.model.dao.User;
import com.egojit.cloud.model.vo.LoginResultVo;
import com.egojit.cloud.model.vo.LoginVo;
import com.egojit.cloud.service.UserService;
import com.egojit.cloud.zookeeper.ZookeeperClinet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ZookeeperClinet clinet;





    /**
     * 通过用户名获取用户
     *
     * @param userName 登录名
     * @return
     */
    @Override
    public LoginResultVo getUserByName(String userName) {
        User user=new User();
        user.setLoginName(userName);
        List<User> users= super.selectAll();
        user= super.selectOne(user);
        if(user!=null){
            LoginResultVo loginResultVo=new LoginResultVo();
            PojoUtils.copObject(user,loginResultVo);
            return loginResultVo;
        }
        return null;
    }

    @Override
    public BaseResult login(LoginVo loginVo) {
        LoginResultVo loginResultVo = this.getUserByName(loginVo.getUserName());
        if (loginResultVo == null) {
            return new BaseResult(new Throwable("用户不存在"));
        }
        if (!loginVo.getPwd().equals(loginResultVo.getPwd())) {
            return new BaseResult(new Throwable("密码不正确！"));
        }
        JSONObject result=new JSONObject();
        result.put("token","test");
        result.put("user",loginResultVo);
        return new BaseResult(result);
    }
}
