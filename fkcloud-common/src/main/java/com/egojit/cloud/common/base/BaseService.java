package com.egojit.cloud.common.base;

import com.egojit.cloud.common.Page;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface BaseService<T> {
    /**
     * 根据对象条件查询数据记录数
     *
     * @param model
     * @return
     */
    int selectCount(T model);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键查询对象
     *
     * @param o
     * @return
     */
    T selectByPrimaryKey(Object o);

    /**
     * 根据对象查询单条对象
     *
     * @param model 查询对象
     * @return
     */
    T selectOne(T model);

    /**
     * 根据对象查询数据
     *
     * @param model 查询对象
     * @return
     */
    List<T> select(T model);

    /**
     * 根据example条件查询数据
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 根据example条件查询分页数据
     *
     * @param example   example条件
     * @param rowBounds 分页条件
     * @return
     */
    List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds);

    /**
     * 选择
     *
     * @param example
     * @return
     */
    T selectOneByExample(Example example);

    /**
     * 根据example条件查询数据记录数
     *
     * @param example
     * @return
     */
    int selectCountByExample(Example example);


    /**
     * 根据对象条件查询分页数据
     *
     * @param model     对象
     * @param rowBounds 分页条件
     * @return 返回数据列表
     */
    List<T> selectByRowBounds(T model, RowBounds rowBounds);


    /**
     * 根据对象条件查询分页数据-返回page对象
     *
     * @param example 对象
     * @return 返回page对象
     */
    Page<T> selectPageByExample(Example example, Page<T> page);

    /**
     * 根据对象条件查询分页数据-返回page对象
     *
     * @param model 对象
     * @return 返回page对象
     */
    Page<T> selectPage(T model, Page<T> page);

    /**
     * 插入对象
     *
     * @param model
     * @return
     */
    int insert(T model);

    /***
     * 通过主键删除对象
     * @param o
     * @return
     */
    int deleteByPrimaryKey(Object o);

    /**
     * 删除对象
     *
     * @param model
     * @return
     */
    int delete(T model);

    /**
     * 删除对象
     *
     * @param ids ids
     * @return
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除对象
     *
     * @param ids ids
     * @return
     */
    int deleteByIds(String[] ids);


    /**
     * 根据主键更新对象-会更新所有字段包括空值
     *
     * @param model
     * @return
     */
    int updateByPrimaryKey(T model);

    /**
     * 根据主键更新对象-只有不为空的属性才会被更新
     *
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(T model);

    /**
     * 删除对象
     *
     * @param o
     * @return
     */
    int deleteByExample(Object o);

    /**
     * 根据example条件更新符合条件的数据-会更新所有字段包括空值
     *
     * @param model
     * @param o
     * @return
     */
    int updateByExample(T model, Object o);

    /**
     * 根据example条件更新符合条件的数据-只有不为空的属性才会被更新
     *
     * @param model
     * @param o
     * @return
     */
    int updateByExampleSelective(T model, Object o);
}
