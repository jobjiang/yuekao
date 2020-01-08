package com.order.mapper;

import com.order.pojo.TbTeacher;
import com.order.pojo.TbTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTeacherMapper {
    int countByExample(TbTeacherExample example);

    int deleteByExample(TbTeacherExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(TbTeacher record);

    int insertSelective(TbTeacher record);

    List<TbTeacher> selectByExample(TbTeacherExample example);

    TbTeacher selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByExample(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByPrimaryKeySelective(TbTeacher record);

    int updateByPrimaryKey(TbTeacher record);
}