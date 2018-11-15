package com.lmgroup.groupbusiness.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/hello")
public class HelloController {

    private static Logger logger = Logger.getLogger(HelloController.class);

    @RequestMapping(value = "/greeting")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/layout");
        mv.addObject("name", "欢迎使用Thymeleaf!");
        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        mv.addObject("name", "欢迎使用Thymeleaf!");
        return mv;
    }
}
