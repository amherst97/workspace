package com.home.conferenceboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.Calculation;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("greetings")
    public String greeting(Map<String, Object> model) {
        model.put("message", "Hello Michael Chen!!");
        // Looking for JPS file name with greeting
        System.out.println(Calculation.calculateArea(3.0));
        Calculation.calculateResult();
        return "greeting";
    }
}
