package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.service.UserService;
import com.campusdual.lituraliaopen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping({"/login"})
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(produces = "application/json")
    public User validateLogin() {
        return User.builder().username("demo").password("demouser").build();
    }

}
