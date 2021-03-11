package com.qf.service;


import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.utils.Page;

public interface VideoService {


    Page<Video> selectVideoByQueryVo(QueryVo queryVo);


    Video selectById(int id);

    int selectCount();

    void update(Video video);
    void choose(Video video);

    void addVideo(Video video);

    boolean delBatchVideos(String[] id);
}
