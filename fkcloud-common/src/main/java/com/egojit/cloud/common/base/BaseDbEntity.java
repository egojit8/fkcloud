package com.egojit.cloud.common.base;

import com.egojit.cloud.common.utils.GuidUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * 基础数据库对象，所有数据库对象必须继承
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public abstract class BaseDbEntity extends Entity {

    /**
     * id
     */
    @Id
    private String id;


    /**
     * 创建时间
     */
    private Date createTime;

    public BaseDbEntity() {
    }

    public BaseDbEntity(String id) {
        this.setId(id);
    }

    /**
     * 新增之前默认处理
     */
    public void preInsert() {
        if (StringUtils.isEmpty(this.getId())) {
            this.setId(GuidUtils.guid());
        }
        //当前时间戳毫秒
        this.createTime =new Date();
    }


    /**
     * 新增之前默认处理
     */
    public void preUpdate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
