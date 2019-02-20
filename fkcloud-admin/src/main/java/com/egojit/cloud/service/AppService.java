package com.egojit.cloud.service;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseService;
import com.egojit.cloud.model.dao.App;

/**
 * app管理逻辑层
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface AppService extends BaseService<App> {
    /**
     * 安装app
     * @param app
     */
    BaseResult install(App app,boolean startNow);

    /**
     * 启动
     * @param containerId  容器id
     */
    BaseResult start(String containerId);

    /**
     * 停止容器
     * @param containerId 容器id
     * @return 结果
     */
    BaseResult stop(String containerId);


    /**
     * 重启
     * @param containerId 容器id
     * @return 结果
     */
    BaseResult restart(String containerId);

    /**
     * 关闭容器端口
     * @param containerId 容器id
     * @return
     */
    BaseResult closeOrOpenPort(String containerId,boolean open);
}
