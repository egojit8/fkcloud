package com.egojit.cloud.controller.impl;

import com.egojit.cloud.common.Page;
import com.egojit.cloud.common.base.BaseController;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.controller.AppController;
import com.egojit.cloud.model.dao.App;
import com.egojit.cloud.service.AppService;
import com.github.dockerjava.api.DockerClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage program
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@RestController
@RequestMapping("/api/v1/admin/app")
public class AppControllerImpl extends BaseController implements AppController {
    private static Logger LOGGER = LoggerFactory.getLogger(AppControllerImpl.class);

    @Autowired
    private AppService service;
    @Autowired
    DockerClient dockerClient;

    /**
     * Get the list of programs
     *
     * @param request request
     * @param key key words eg. The name of the program
     * @return
     */
    @GetMapping
    @Override
    public BaseResult list(HttpServletRequest request, String key) {
        Page<App> page = new Page<>(request);
        Example example = new Example(App.class);
        if (StringUtils.isNotEmpty(key)) {
            example.createCriteria().andLike("name", "%" + key + "%");
        }
        page = service.selectPageByExample(example, page);
        return new BaseResult(page);
    }

    /**
     * Get program details
     *
     * @param id 应用id
     * @return program details
     */
    @GetMapping("/{id}")
    @Override
    public BaseResult detail(@PathVariable(name = "id", required = true) String id) {
        App app = service.selectByPrimaryKey(id);
        return new BaseResult(app);
    }

    /**
     * Install the program
     *
     * @param app app信息
     * @return install result
     */
    @PutMapping
    @Override
    public BaseResult install(@RequestBody App app) {
        return service.install(app, true);
    }

    /**
     * Start the progarm
     *
     * @param containerId Container id
     * @return start result
     */
    @PutMapping("/start/{containerId}")
    @Override
    public BaseResult start(@PathVariable(name = "containerId") String containerId) {
        return service.start(containerId);
    }

    /**
     * Stop the program
     *
     * @param containerId Container id
     * @return stop result
     */
    @PutMapping("/stop/{containerId}")
    @Override
    public BaseResult stop(@PathVariable(name = "containerId") String containerId) {
        return service.stop(containerId);
    }

    /**
     * restart the program
     *
     * @param containerId Container id
     * @return restart result
     */
    @PutMapping("/restart/{containerId}")
    @Override
    public BaseResult restart(@PathVariable(name = "containerId") String containerId) {
        return service.restart(containerId);
    }

}
