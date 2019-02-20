package com.egojit.cloud.service;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseService;
import com.egojit.cloud.model.dao.RuningApp;

/**
 * 运行时APP管理服务
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface RuningAppService extends BaseService<RuningApp> {

    /**
     * 获取应用并且包含分组
     * @return
     */
    BaseResult list();

    /**
     * 容器详情
     * @param id 容器id
     * @return 容器详情
     */
    BaseResult detail(String id);

    /**
     * 删除
     * @param id 
     * @return
     */
    BaseResult delete(String id);


}
