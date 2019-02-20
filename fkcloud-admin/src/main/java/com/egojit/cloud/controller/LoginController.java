package com.egojit.cloud.controller;

import com.egojit.cloud.common.base.BaseController;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.model.vo.LoginVo;
import com.egojit.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录接口
 * @author 高露 QQ：408365330
 */
@RestController
@RequestMapping("/sso")
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public BaseResult login(@RequestBody LoginVo parm){
        BaseResult result= userService.login(parm);
        return result;
    }
}
