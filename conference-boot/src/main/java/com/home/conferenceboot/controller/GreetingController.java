package com.home.conferenceboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("greetings")
    public String greeting(Map<String, Object> model) {
        model.put("message", "Hello Michael Chen!!");
        // Looking for JPS file name with greeting
        return "greeting";
    }

    @GetMapping("thyme")
    public String thyme(Map<String, Object> model) {
        model.put("message", "Hello Thymeleaf!!");
        // Looking for JPS file name with greeting
        return "thyme";
    }
}
