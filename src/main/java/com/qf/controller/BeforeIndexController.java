package com.qf.controller;

import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("subject")
public class BeforeIndexController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("selectAll")
    public String selectAll(Model model) {
       List<Subject> subjectList = subjectService.selectAll();
       model.addAttribute("subjectList",subjectList);
       return "/WEB-INF/jsp/before/index.jsp";
    }
}
