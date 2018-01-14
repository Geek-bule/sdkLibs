package com.herman.demo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by herman on 2017/11/30.
 */
@Controller
@RequestMapping("template")
public class TemplateController {

    @RequestMapping("index")
    public String template() {
        return "template/template";
    }

}
