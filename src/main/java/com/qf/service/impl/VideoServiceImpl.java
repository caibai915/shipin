package com.qf.service.impl;


import com.qf.dao.VideoMapper;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.service.VideoService;
import com.qf.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;


    @Override
    public Page<Video> selectVideoByQueryVo(QueryVo queryVo) {
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getSize());
        List<Video> videoList = videoMapper.selectVideoByQueryVo(queryVo);
        Integer total = videoMapper.selectCountByQueryVo(queryVo);

        Page<Video> videoPage = new Page<>();
        videoPage.setRows(videoList);
        videoPage.setTotal(total);
        videoPage.setPage(queryVo.getPage());
        videoPage.setSize(queryVo.getSize());

        return videoPage;
    }

    @Override
    public Video selectById(int id) {
       Video video = videoMapper.selectById(id);
        return video;
    }

    @Override
    public int selectCount() {

        return videoMapper.selectCount();
    }

    @Override
    public void update(Video video) {
        videoMapper.update(video);
    }

    @Override
    public void choose(Video video) {
        if (video.getId() != null) {
            videoMapper.update(video);
        } else {
            videoMapper.addVideo(video);
        }

    }
    @Override
    public void addVideo(Video video) {
        videoMapper.addVideo(video);
    }

    @Override
    public boolean delBatchVideos(String[] id) {
        Boolean flag = true;
        for (String s : id) {
            if(!videoMapper.delBatchVideos(s)) {
                flag = false;
            } else{
                flag =true;
            }
        }
        return flag;
    }


}
