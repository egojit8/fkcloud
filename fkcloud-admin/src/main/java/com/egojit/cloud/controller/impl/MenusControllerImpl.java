package com.egojit.cloud.controller.impl;

import com.egojit.cloud.common.base.BaseController;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.controller.MenusController;
import com.egojit.cloud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@RestController
@RequestMapping("/api/v1/menus")
public class MenusControllerImpl  extends BaseController implements MenusController{

    @Autowired
    private MenuService menuService;
    /**
     * 获取菜单列表
     * @return
     */
    @GetMapping
    @Override
    public BaseResult list() {
        return menuService.getList();
    }


}
