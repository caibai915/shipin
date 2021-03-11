package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import com.qf.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("video")
public class VideoListController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @RequestMapping("showVideo")
    public String showVideo(Model model,String subjectName,int videoId) {
        model.addAttribute("subjectName",subjectName);

        System.out.println(subjectName);
        List<Subject> subjectList = subjectService.selectAll();
        model.addAttribute("subjectList",subjectList);
        Video video = videoService.selectById(videoId);
        System.out.println("++++++++++++++++++");
        System.out.println(video);
        System.out.println(video.getCourseId());
        model.addAttribute("video",video);
        System.out.println("**************");
        Course course = courseService.selectCourseById(video.getCourseId());
        System.out.println(course);
        model.addAttribute("course",course);

        return "/WEB-INF/jsp/before/section.jsp";
    }

    @RequestMapping("list")
    public String findAll(QueryVo queryVo, Model model) {
        model.addAttribute("queryVo", queryVo);
        System.out.println(queryVo);
        int count = videoService.selectCount();
        int countSize = 0;
        if (count % queryVo.getSize() == 0) {
            countSize = count / queryVo.getSize();
        } else {
            countSize = count / queryVo.getSize() + 1;
        }
        model.addAttribute("countSize", countSize);
        Page<Video> videoPage = videoService.selectVideoByQueryVo(queryVo);
        model.addAttribute("page", videoPage);
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList", speakerList);
        for (Speaker speaker : speakerList) {
            System.out.println(speaker);
        }

        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "/WEB-INF/jsp/behind/videoList.jsp";
    }

    @RequestMapping("edit")
    public String selectById(int id, Model model) {
        System.out.println(id);
        Video video = videoService.selectById(id);
        System.out.println(video);
        model.addAttribute("video", video);
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList", speakerList);
        return "/WEB-INF/jsp/behind/addVideo.jsp";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Video video) {
        videoService.choose(video);
        return "redirect:list";
    }

    @RequestMapping("/addVideo")
    public String addVideo(Model model) {
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList", speakerList);

        return "/WEB-INF/jsp/behind/addVideo.jsp";
    }

    @RequestMapping("delBatchVideos")
    public String delBatchVideos(String[] ids) {
        videoService.delBatchVideos(ids);
        return "redirect:list";
    }

    @RequestMapping("/videoDel")
    @ResponseBody
    public String videoDel(String id) {
        String[] ids = new String[1];
        ids[0] = id;
        if (videoService.delBatchVideos(ids)) {
            return "success";
        } else {
            return "failed";
        }
    }
}
