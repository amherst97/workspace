package com.home.conferenceboot.controller;

import com.home.conferenceboot.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "firstname", defaultValue = "Dansong")  String firstName,
                        @RequestParam(value = "lastname", defaultValue = "Chen")  String lastName,
                        @RequestParam(value = "age", defaultValue = "42")  int age) {

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);

        return user;
    }

    @PostMapping("/user")
    public User postuser(User user) {
        System.out.println("User firstname: " + user.getFirstName());
        System.out.println("User lasttname: " + user.getLastName());
        System.out.println("User age: " + user.getAge());
        return user;
    }
}
