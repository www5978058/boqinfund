package com.shyb.boqinfund.service.impl;

import com.shyb.boqinfund.mapper.BaseMapper;
import com.shyb.boqinfund.entity.BaseEntity;
import com.shyb.boqinfund.entity.BaseExample;
import com.shyb.boqinfund.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wzh
 * @date 2019/8/15 - 16:17
 */
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl<T extends BaseExample,E extends BaseEntity,M extends BaseMapper<T,E>> implements BaseService<T,E> {
    @Autowired
    private M mapper;
    @Override
    public int add(E e) {
        return mapper.insertSelective(e);
    }

    @Override
    public int deleteByKey(int id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(T t) {
        return mapper.deleteByExample(t);
    }

    @Override
    public int updateByKey(E e) {
        return mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public int update(E e, T t) {
        return mapper.updateByExampleSelective(e,t);
    }

    @Override
    @Transactional(readOnly = true)
    public E selectByKey(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> select(T t) {
        return mapper.selectByExample(t);
    }
}
