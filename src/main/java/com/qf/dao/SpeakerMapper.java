package com.qf.dao;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.pojo.SpeakerExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpeakerMapper {
    int countByExample(SpeakerExample example);

    List<Speaker> selectByExample();

    List<Speaker> selectSpeakerByQueryVo(QueryVo queryVo);

    boolean update(int id);

    Speaker selectById(int id);

    boolean addSpeaker(int id);

    boolean delSpeakerById(int id);
}