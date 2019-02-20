package com.egojit.cloud.service.Impl;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseServiceImpl;
import com.egojit.cloud.mapper.MenusMapper;
import com.egojit.cloud.model.dao.Menu;
import com.egojit.cloud.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenusMapper, Menu> implements MenuService {

    /**
     * 获取所有菜单
     * @return
     */
    @Override
    public BaseResult getList() {
        List<Menu> list= super.selectAll();
        return  new BaseResult(list);
    }
}
