package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.SpeakerService;
import com.qf.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    private SpeakerMapper speakerMapper;

    public List<Speaker> findAll() {
        return speakerMapper.selectByExample();
    }

    @Override
    public int selectCount() {
        return speakerMapper.countByExample(null);
    }

    @Override
    public Page<Speaker> selectVideoByQueryVo(QueryVo queryVo) {
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getSize());
        List<Speaker> speakerList = speakerMapper.selectSpeakerByQueryVo(queryVo);
        for (Speaker speaker : speakerList) {
            System.out.println(speaker);
        }
        Page<Speaker> speakerPage = new Page<>();
        speakerPage.setRows(speakerList);
        speakerPage.setPage(queryVo.getPage());
        speakerPage.setSize(queryVo.getSize());

        return speakerPage;
    }


    @Override
    public Speaker selectById(int id) {
        return speakerMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(String id) {
        if(id == null) {
            return speakerMapper.addSpeaker(Integer.parseInt(id));
        } else {
           return speakerMapper.update(Integer.parseInt(id));
        }
    }

    @Override
    public boolean delSpeakerById(int id) {
        Boolean flag = true;
        if(!speakerMapper.delSpeakerById(id)) {
            flag = false;
        } else{
            flag =true;
        }

        return flag;
    }
}
