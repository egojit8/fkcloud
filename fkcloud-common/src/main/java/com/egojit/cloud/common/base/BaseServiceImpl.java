package com.egojit.cloud.common.base;

import com.egojit.cloud.common.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基础服务类
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Transactional
public abstract class BaseServiceImpl<M extends Mapper<T>, T extends BaseDbEntity> implements BaseService<T> {
    /**
     * 日志对象
     */
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M mapper;

    private Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return tClass;
    }

    /**
     * 根据对象条件查询数据记录数
     *
     * @param model
     * @return
     */
    @Override
    public int selectCount(T model) {
        return mapper.selectCount(model);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据主键查询对象
     *
     * @param o
     * @return
     */
    @Override
    public T selectByPrimaryKey(Object o) {
        return mapper.selectByPrimaryKey(o);
    }

    /**
     * 根据对象查询单条对象
     *
     * @param model 查询对象
     * @return
     */
    @Override
    public T selectOne(T model) {
        return mapper.selectOne(model);
    }

    /**
     * 根据对象查询数据
     *
     * @param model 查询对象
     * @return
     */
    @Override
    public List<T> select(T model) {
        return mapper.select(model);
    }

    /**
     * 根据example条件查询数据
     *
     * @param example
     * @return
     */
    @Override
    public List<T> selectByExample(Example example) {
        if (example == null) {
            example = new Example(getTClass());
        }
        return mapper.selectByExample(example);
    }

    /**
     * 根据example条件查询分页数据
     *
     * @param example   example条件
     * @param rowBounds 分页条件
     * @return
     */
    @Override
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        if (example == null) {

            example = new Example(getTClass());
        }
//        String order= example.getOrderByClause();
//        if(StringUtils.isEmpty(order)){
//            example.setOrderByClause(" create_date DESC");
//        }
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * 根据example条件查询数据记录数
     *
     * @param example
     * @return
     */
    @Override
    public int selectCountByExample(Example example) {
        return mapper.selectCountByExample(example);
    }


    @Override
    public T selectOneByExample(Example example) {
        T model = selectOneByExample(example);
        return model;
    }

    /**
     * 根据对象条件查询分页数据
     *
     * @param model     对象
     * @param rowBounds 分页条件
     * @return 返回数据列表
     */
    @Override
    public List<T> selectByRowBounds(T model, RowBounds rowBounds) {

        return mapper.selectByRowBounds(model, rowBounds);
    }


    /**
     * 根据对象条件查询分页数据-返回page对象
     *
     * @param example 对象
     * @return 返回page对象
     */
    @Override
    public Page<T> selectPageByExample(Example example, Page<T> page) {
        if(page.isNeedPage()) {
            long count = this.selectCountByExample(example);
            page.setTotalCount(count);
            PageHelper.startPage(page.getPageIndex(), page.getPageSize());
        }
        List<T> list = this.selectByExample(example);
        return page.setList(list);
    }

    /**
     * 根据对象条件查询分页数据-返回page对象
     *
     * @param model 对象
     * @return 返回page对象
     */
    @Override
    public Page<T> selectPage(T model, Page<T> page) {
        if(page.isNeedPage()){
            long count = selectCount(model);
            page.setTotalCount(count);
            PageHelper.startPage(page.getPageIndex(), page.getPageSize());
        }
        List<T> list = this.select(model);
        return page.setList(list);
    }

    /**
     * 插入对象
     *
     * @param model
     * @return
     */
    @Override
    public int insert(T model) {
        model.preInsert();
        return mapper.insert(model);
    }

    /***
     * 通过主键删除对象
     * @param o
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Object o) {
        return mapper.deleteByPrimaryKey(o);
    }

    /**
     * 删除对象
     *
     * @param model
     * @return
     */
    @Override
    public int delete(T model) {
        return mapper.delete(model);
    }

    /**
     * 删除对象
     *
     * @param ids ids
     * @return
     */
    @Override
    public int deleteByIds(List<String> ids) {
        int count = 0;
        if (ids != null) {
            for (String id : ids) {
                count += mapper.deleteByPrimaryKey(id);
            }
        }
        return count;
    }

    /**
     * 删除对象
     *
     * @param ids ids
     * @return
     */
    @Override
    public int deleteByIds(String[] ids) {
        int count = 0;
        if (ids != null) {
            for (String id : ids) {
                count += mapper.deleteByPrimaryKey(id);
            }
        }
        return count;
    }


    /**
     * 根据主键更新对象-会更新所有字段包括空值
     *
     * @param model
     * @return
     */
    @Override
    public int updateByPrimaryKey(T model) {
        model.preUpdate();
        return mapper.updateByPrimaryKey(model);
    }

    /**
     * 根据主键更新对象-只有不为空的属性才会被更新
     *
     * @param model
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(T model) {
        model.preUpdate();
        return mapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 删除对象
     *
     * @param o
     * @return
     */
    @Override
    public int deleteByExample(Object o) {
        return mapper.deleteByExample(o);
    }

    /**
     * 根据example条件更新符合条件的数据-会更新所有字段包括空值
     *
     * @param model
     * @param o
     * @return
     */
    @Override
    public int updateByExample(T model, Object o) {
        model.preUpdate();
        return mapper.updateByExample(model, o);
    }

    /**
     * 根据example条件更新符合条件的数据-只有不为空的属性才会被更新
     *
     * @param model
     * @param o
     * @return
     */
    @Override
    public int updateByExampleSelective(T model, Object o) {
        model.preUpdate();
        return mapper.updateByExampleSelective(model, o);
    }
}
