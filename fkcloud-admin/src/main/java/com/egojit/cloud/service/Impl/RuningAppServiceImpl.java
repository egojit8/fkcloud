package com.egojit.cloud.service.Impl;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseServiceImpl;
import com.egojit.cloud.common.utils.PojoUtils;
import com.egojit.cloud.mapper.RuningAppMapper;
import com.egojit.cloud.model.dao.RuningApp;
import com.egojit.cloud.model.dao.RuningGroup;
import com.egojit.cloud.model.vo.RuningAppVo;
import com.egojit.cloud.model.vo.RuningGroupVo;
import com.egojit.cloud.service.RuningAppService;
import com.egojit.cloud.service.RuningGroupService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.NotModifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时APP管理
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class RuningAppServiceImpl extends BaseServiceImpl<RuningAppMapper, RuningApp> implements RuningAppService {

    @Autowired
    RuningGroupService runingGroupService;

    @Autowired
    DockerClient dockerClient;

    /**
     * 查询所有应用包含应用分组
     *
     * @return
     */
    @Override
    public BaseResult list() {
        List<RuningGroup> groups = runingGroupService.selectAll();
        List<RuningApp> apps = selectAll();
        List<RuningAppVo> list = new ArrayList<>();
        if (apps != null) {
            for (RuningApp app : apps) {
                RuningAppVo runingAppVo = new RuningAppVo();
                PojoUtils.copObject(app, runingAppVo);
                RuningGroupVo groupVo = new RuningGroupVo();
                RuningGroup group = getAppGroup(groups, app);
                PojoUtils.copObject(group, groupVo);
                runingAppVo.setGroup(groupVo);
                list.add(runingAppVo);
            }
        }
        return new BaseResult(list);
    }

    /**
     * 容器详情
     *
     * @param id 容器id
     * @return 容器详情
     */
    @Override
    public BaseResult detail(String id) {
        InspectContainerResponse response = dockerClient.inspectContainerCmd(id).exec();
        return new BaseResult(response);
    }

    private RuningGroup getAppGroup(List<RuningGroup> groups, RuningApp app) {
        if (groups != null) {
            for (RuningGroup group : groups) {
                if (group.getId().endsWith(app.getGroupId())) {
                    return group;
                }
            }
        }
        return null;
    }


    /**
     * 删除安装的程序
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult delete(String id) {
        try {
            dockerClient.stopContainerCmd(id).exec();
            LOGGER.debug("停止容器{}成功:", id);
        } catch (NotFoundException ex) {
            LOGGER.warn("容器{}不存在", id);
        } catch (NotModifiedException ex) {
            LOGGER.error("容器状态无法修改{}", ex);
        }
        try {
            dockerClient.removeContainerCmd(id).exec();
            LOGGER.debug("删除容器{}成功:", id);
        } catch (NotFoundException ex) {
            LOGGER.warn("容器{}不存在", id);
        }
        int count = deleteByPrimaryKey(id);
        LOGGER.debug("删除数:{}", count);
        return count > 0 ? new BaseResult("删除成功") : new BaseResult(new Throwable("删除失败，稍后重试"));
    }


}
