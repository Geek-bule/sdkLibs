package com.herman.demo.game.controller;

import com.herman.common.controller.BaseController;
import com.herman.demo.entity.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by herman on 2017/11/27.
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("{page}")
    @ResponseBody
    public ModelAndView index(@PathVariable("page") String page) {
        return new ModelAndView("test/" + page);
    }

    @RequestMapping("data")
    @ResponseBody
    public Test getTest() {
        return testService.getTest(1L);
    }

}
