package com.timhuo.dianping.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * /admin/admin   /管理员模块/管理员
 *
 * @description: 管理员controller
 * @author: Tim_Huo
 * @created: 2020/10/04 07:40
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminController {


    @RequestMapping("/admin/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        return modelAndView;
    }
}
