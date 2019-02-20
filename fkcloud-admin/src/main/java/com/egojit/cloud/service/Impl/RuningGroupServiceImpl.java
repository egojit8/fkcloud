package com.egojit.cloud.service.Impl;

import com.egojit.cloud.common.Page;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseServiceImpl;
import com.egojit.cloud.mapper.RuningGroupMapper;
import com.egojit.cloud.model.dao.RuningGroup;
import com.egojit.cloud.service.RuningGroupService;
import org.springframework.stereotype.Service;

/**
 * group for container
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class RuningGroupServiceImpl extends BaseServiceImpl<RuningGroupMapper, RuningGroup> implements RuningGroupService {


    @Override
    public BaseResult list(Page<RuningGroup> page) {
        return null;
    }
}
