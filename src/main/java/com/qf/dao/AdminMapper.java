package com.qf.dao;

import com.qf.pojo.Admin;
import com.qf.pojo.AdminExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    Admin selectByUsernameAndPassword( Admin admin);
}