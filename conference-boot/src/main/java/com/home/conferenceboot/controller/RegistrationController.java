package com.home.conferenceboot.controller;

import com.home.conferenceboot.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {

        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@Valid @ModelAttribute("registration") Registration registration,
                                  BindingResult result) {
        System.out.println("Registration: " + registration.getName());

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "registration";
        }

        // the "redirect" is a keyword for implement post-redirect-get to avoid resubmit form
        return "redirect:registration";
    }
}
