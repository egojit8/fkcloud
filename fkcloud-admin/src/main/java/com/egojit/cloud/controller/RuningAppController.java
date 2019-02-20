package com.egojit.cloud.controller;

import com.egojit.cloud.common.base.BaseResult;

/**
 * Manage running programs
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface RuningAppController {

    /**
     * Get the deployed program
     * @return list of deployed program
     */
    BaseResult list();


    /**
     *  Get container by container id
     * @param id id of container
     * @return Dontainer details
     */
    BaseResult detail(String id);

    /**
     * 删除程序
     * @param id 运行程序的id
     * @return
     */
    BaseResult delete(String id);

    /**
     * 关闭或者打开端口
     * @param containerId 容器id
     * @param open 关闭或者打开
     * @return
     */
    BaseResult closeOrOpenPort(String containerId, boolean open);
}
