package com.qf.controller;


import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/loginView")
    public String loginView() {
        return "/WEB-INF/jsp/behind/login.jsp";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin, HttpServletRequest request) {
        admin = adminService.login(admin);
        if (null != admin) {
            HttpSession session = request.getSession();
            session.setAttribute("username",admin.getUsername());
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "redirect:loginView";
    }
}
