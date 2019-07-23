package com.aiti.preauthorizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private PasswordEncoder standardPasswordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        // String encode = standardPasswordEncoder.encode("admin");
        return "Hello! " + authentication.getName(); // + " pass: " + encode;
    }

}
