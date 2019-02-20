package com.egojit.cloud.service;

import com.egojit.cloud.common.Page;
import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseService;
import com.egojit.cloud.model.dao.RuningGroup;

/**
 * 容器分组
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface RuningGroupService extends BaseService<RuningGroup> {



    BaseResult list(Page<RuningGroup> page);
}
