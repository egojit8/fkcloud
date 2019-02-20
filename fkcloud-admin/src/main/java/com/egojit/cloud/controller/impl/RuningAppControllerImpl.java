package com.egojit.cloud.controller.impl;

import com.egojit.cloud.common.base.BaseController;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.controller.RuningAppController;
import com.egojit.cloud.service.AppService;
import com.egojit.cloud.service.RuningAppService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Manage running programs
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@RestController
@RequestMapping("/api/v1/admin/runing")
public class RuningAppControllerImpl extends BaseController implements RuningAppController {
    Logger logger = LoggerFactory.getLogger(RuningAppControllerImpl.class);

    @Autowired
    RuningAppService service;

    @Autowired
    AppService appService;


    /**
     * Get the deployed program
     *
     * @return list of deployed program
     */
    @Override
    @GetMapping
    public BaseResult list() {
        return service.list();
    }

    /**
     * Get container by container id
     *
     * @param id id of container
     * @return Dontainer details
     */
    @Override
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable(name = "id") String id) {
        return service.detail(id);
    }

    /**
     * 删除
     *
     * @param id 运行程序id
     * @return
     */
    @Override
    @DeleteMapping("/{id}")
    public BaseResult delete(@PathVariable(name = "id") String id) {
        return service.delete(id);
    }

    /**
     * 打开或者关闭端口
     * @param id 容器id
     * @param open 关闭或者打开
     * @return
     */
    @Override
    @PutMapping("/changePortState/{id}/{open}")
    public BaseResult closeOrOpenPort(@PathVariable(name = "id") String id, @PathVariable(name = "open") boolean open) {
        return appService.closeOrOpenPort(id,open);
    }
}
