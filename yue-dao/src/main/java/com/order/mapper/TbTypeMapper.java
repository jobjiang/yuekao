package com.order.mapper;

import com.order.pojo.TbType;
import com.order.pojo.TbTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTypeMapper {
    int countByExample(TbTypeExample example);

    int deleteByExample(TbTypeExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(TbType record);

    int insertSelective(TbType record);

    List<TbType> selectByExample(TbTypeExample example);

    TbType selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") TbType record, @Param("example") TbTypeExample example);

    int updateByExample(@Param("record") TbType record, @Param("example") TbTypeExample example);

    int updateByPrimaryKeySelective(TbType record);

    int updateByPrimaryKey(TbType record);
}