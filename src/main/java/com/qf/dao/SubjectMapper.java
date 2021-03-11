package com.qf.dao;

import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubjectMapper {






    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Integer id);


}