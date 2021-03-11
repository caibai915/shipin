package com.qf.controller;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.SpeakerService;
import com.qf.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("speaker")
public class SpeakerController {
    @Autowired
    private SpeakerService speakerService;
    @RequestMapping("showSpeakerList")
    public String showSpeakerList(QueryVo queryVo, Model model) {
        model.addAttribute(queryVo);
        int count = speakerService.selectCount();
        int countSize = 0;
        if (count % queryVo.getSize() == 0) {
            countSize = count / queryVo.getSize();
        } else {
            countSize = count / queryVo.getSize() + 1;
        }
        model.addAttribute("countSize", countSize);
        Page<Speaker> speaker = speakerService.selectVideoByQueryVo(queryVo);
        model.addAttribute("page", speaker);
        return "/WEB-INF/jsp/behind/speakerList.jsp";
    }
    @RequestMapping("/edit")
    public String selectById(int id,Model model) {
        Speaker speaker = speakerService.selectById(id);
        System.out.println(speaker);
        model.addAttribute("speaker",speaker);
        return "/WEB-INF/jsp/behind/addSpeaker.jsp";
    }
    @RequestMapping("/addSpeaker")
    public String addSpeaker() {
        return "/WEB-INF/jsp/behind/addSpeaker.jsp";
    }
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate (String id) {

        speakerService.saveOrUpdate(id);
        return "redirect:showSpeakerList";
    }
    @RequestMapping("delSpeakerById")
    public String delSpeakerById(int id) {
        speakerService.delSpeakerById(id);
        return "redirect:showSpeakerList";
    }

    @RequestMapping("/speakerDel")
    @ResponseBody
    public String speakerDel(int id) {
        if (speakerService.delSpeakerById(id)) {
            return "success";
        } else {
            return "failed";
        }
    }
}
