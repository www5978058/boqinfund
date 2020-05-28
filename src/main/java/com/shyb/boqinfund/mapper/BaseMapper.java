package com.shyb.boqinfund.mapper;

import com.shyb.boqinfund.entity.BaseEntity;
import com.shyb.boqinfund.entity.BaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wzh
 * @date 2019/8/15 - 15:44
 */
public interface BaseMapper<T extends BaseExample,E extends BaseEntity> {
    int countByExample(T example);

    int deleteByExample(T example);

    int deleteByPrimaryKey(Integer id);

    int insert(E record);

    int insertSelective(E record);

    List<E> selectByExample(T example);

    E selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") E record, @Param("example") T example);

    int updateByExample(@Param("record") E record, @Param("example") T example);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);
}
