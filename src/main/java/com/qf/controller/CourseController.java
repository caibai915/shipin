package com.qf.controller;


import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.service.CourseService;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @RequestMapping("showCourseList")
    public String showCourseList(){
        return "/WEB-INF/jsp/404.jsp";
    }
    @RequestMapping("/course/{subjectId}")
    public String findCourse(@PathVariable int subjectId,Model model) {

        List<Subject> subjectList = subjectService.selectAll();
        model.addAttribute("subjectList",subjectList);
        Subject subject = subjectService.selectSubjectById(subjectId);
        model.addAttribute("subject",subject);
        System.out.println(subject);
        return "/WEB-INF/jsp/before/course.jsp";
    }
}
