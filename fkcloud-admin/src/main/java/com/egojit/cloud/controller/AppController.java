package com.egojit.cloud.controller;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.model.dao.App;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage program
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface AppController {

    /**
     * 应用列表
     * @param request
     * @param key
     * @return
     */
    BaseResult list(HttpServletRequest request, String key);

    /**
     * 应用详情
     *
     * @param id 应用id
     * @return
     */
    BaseResult detail(@PathVariable(name = "id", required = true) String id);


    /**
     * 安装
     * @param app app信息
     */
    BaseResult install(App app);


    /**
     * 启动
     * @param containerId Container id
     * @return 启动结果
     */
    BaseResult start(String containerId);

    /**
     * 停止
     * @param containerId  Container id
     * @return 启动结果
     */
    BaseResult stop(String containerId);

    /**
     * 重启
     * @param containerId Container id
     * @return 启动结果
     */
    BaseResult restart(String containerId);

}
