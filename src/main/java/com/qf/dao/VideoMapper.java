package com.qf.dao;


import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {

    List<Video> selectVideoByQueryVo(QueryVo queryVo);

    Integer selectCountByQueryVo(QueryVo queryVo);

    Video selectById(int id);

    int selectCount();

    void update(Video video);

    void addVideo(Video video);

    boolean delBatchVideos(String id);
}
