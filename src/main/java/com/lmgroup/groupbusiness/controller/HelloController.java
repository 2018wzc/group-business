package com.lmgroup.groupbusiness.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmgroup.groupbusiness.domain.Hello;
import com.lmgroup.groupbusiness.service.HelloService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/hello")
public class HelloController {

    private static Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping("/query")
    public Hello testQuery(HttpServletRequest req, HttpServletResponse resp, Model model) {
        int id = Integer.parseInt(req.getParameter("id"));
        logger.info("id:" + id);


        return helloService.selectHello(id);
    }
}
