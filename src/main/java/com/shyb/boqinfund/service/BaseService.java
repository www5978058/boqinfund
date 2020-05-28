package com.shyb.boqinfund.service;

import com.shyb.boqinfund.entity.BaseEntity;
import com.shyb.boqinfund.entity.BaseExample;

import java.util.List;

/**
 * @author wzh
 * @date 2019/8/15 - 16:04
 */
public interface BaseService<T extends BaseExample,E extends BaseEntity> {
    /**
     * 添加对象
     *
     * @param e
     * @return
     */
    int add(E e);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteByKey(int id);

    /**
     * 根据条件删除
     *
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 根据id修改
     *
     * @param e
     * @return
     */
    int updateByKey(E e);

    /**
     * 根据条件修改
     *
     * @param e
     * @param t
     * @return
     */
    int update(E e, T t);

    /**
     * 根据主键查找
     *
     * @param id
     * @return
     */
    E selectByKey(int id);

    /**
     * 根据条件查找
     *
     * @param t
     * @return
     */
    List<E> select(T t);

}
