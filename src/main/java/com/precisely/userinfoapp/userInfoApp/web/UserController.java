package com.precisely.userinfoapp.userInfoApp.web;

import com.precisely.userinfoapp.userInfoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String rootView () {
        return "index";
    }
}
