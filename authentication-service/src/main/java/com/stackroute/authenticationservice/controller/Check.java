package com.stackroute.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class Check {
    @GetMapping("/check")
    public void check(){
        System.out.println("Checked");
    }
}
