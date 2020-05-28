package com.shyb.boqinfund.controller;

import com.shyb.boqinfund.entity.Admin;
import com.shyb.boqinfund.entity.AdminExample;
import com.shyb.boqinfund.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wzh
 * @date 2019/8/16 - 10:29
 */
@Controller
@RequestMapping("admin")
public class LoginController {
    @Autowired
    AdminService adminService;
    @RequestMapping(value = {"","/"})
    public String index(HttpSession session){
        if (session.getAttribute("user") != null) {
            return "redirect:/fund/showAllFundPrice";
        }
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, ModelMap modelMap){

        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<Admin> list = adminService.select(example);
        if (list.size() == 0) {
            modelMap.addAttribute("login_error","账号或密码错误");
            return "login";
        }
        session.setAttribute("user",list.get(0));
        return "redirect:/fund/showAllFundPrice";
    }
}
