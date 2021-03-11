package com.qf.service;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.utils.Page;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    int selectCount();

    Page<Speaker> selectVideoByQueryVo(QueryVo queryVo);


    Speaker selectById(int id);

    boolean saveOrUpdate(String id);

    boolean delSpeakerById(int id);
}
