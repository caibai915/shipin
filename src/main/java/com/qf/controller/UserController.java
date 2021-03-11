package com.qf.controller;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.ImageCut;
import com.qf.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("loginUser")
    @ResponseBody
    public String login(User user, HttpServletRequest request) {
        User select = userService.select(user);
        if( select!= null) {
            HttpSession session = request.getSession();
            session.setAttribute("user",select);
            return "success";
        } else {
            return "false";
        }
    }
    @RequestMapping("forgetPassword")
    public String forgetPassword(){
        return "/WEB-INF/jsp/before/forget_password.jsp";
    }


    @RequestMapping("showMyProfile")
    public String showMyProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.select((User)session.getAttribute("user"));
        session.setAttribute("user",user);
        System.out.println(user);
        return "/WEB-INF/jsp/before/my_profile.jsp";
    }

    @RequestMapping("changeProfile")
    public String changeProfile() {
        return "/WEB-INF/jsp/before/change_profile.jsp";
    }
    @RequestMapping("updateUser")
    public String updateUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String name = request.getParameter("nickName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        user.setNickName(name);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setAddress(address);
        userService.updateUser(user);
        return "redirect:showMyProfile";
    }
    @RequestMapping("passwordSafe")
    public String passwordSafe() {
        return "/WEB-INF/jsp/before/password_safe.jsp";
    }
    @RequestMapping("updatePassword")
    public String updatePassword(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String newPassword = request.getParameter("newPassword");
        user.setPassword(newPassword);
        System.out.println("----------------"+user);
       userService.updateUserByPassword(user);
        session.setAttribute("user",user);
        System.out.println("+++++++++++++++"+user);
        return "redirect:/user/showMyProfile";
    }
    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        if("".equals(userService.find(oldPassword))) {
            return "false";
        } else {
             return "success";
        }
    }
    @RequestMapping("/loginOut2")
    public String loginOut2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userAccount");
        return "redirect:/index.jsp";
    }
    @RequestMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userAccount");
        return "success";
    }

    @RequestMapping("/changeAvatar")
    public String changeAvatar() {
        return "/WEB-INF/jsp/before/change_avatar.jsp";
    }

    @RequestMapping("/upLoadImage")
    public String upLoadImage(@RequestParam("image_file") MultipartFile imageFile, String x1, String x2, String y1, String y2, HttpServletRequest request) throws  IOException {
        String path = "F:\\Java\\apache-tomcat-8.5.61\\webapps\\img";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filename = imageFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + filename;
        imageFile.transferTo(new File(path, filename));
        int x1Int = (int) Double.parseDouble(x1);
        int x2Int = (int) Double.parseDouble(x2);
        int y1Int = (int) Double.parseDouble(y1);
        int y2Int = (int) Double.parseDouble(y2);
        new ImageCut().cutImage(path + "/" + filename, x1Int, y1Int, x2Int - x1Int, y2Int - y1Int);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setImgUrl(filename);
        userService.updateUser(user);
        return "redirect:/user/showMyProfile";
    }
    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.insertUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return "success";
    }
    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(String email) {
        if(userService.selectUserByEmail(email) != null) {
            return "false";
        } else {
            return "success";
        }
    }
    @RequestMapping("sendEmail")
    @ResponseBody
    public String checkEmail(HttpServletRequest request){
        String email = request.getParameter("email");
        User user = userService.selectUserByEmail(email);
        if(user != null) {
            String validateCode = MailUtils.getValidateCode(7);
            boolean code = MailUtils.sendMail(email, "" + validateCode, "验证码：");
            if(code) {
                HttpSession session = request.getSession();
                session.setAttribute("email",email);
                session.setAttribute("code",validateCode);
                return "success";
            } else {
                return "false";
            }
        }
        return "false";
    }
    @RequestMapping("validateEmailCode")
    public String validateEmailCode(HttpServletRequest request,String code) {
        String code1 = (String)request.getSession().getAttribute("code");
        if(code.equals(code1)) {
            return "/WEB-INF/jsp/before/reset_password.jsp";
        } else {
            return "/WEB-INF/jsp/before/forget_password.jsp";
        }
    }
}
