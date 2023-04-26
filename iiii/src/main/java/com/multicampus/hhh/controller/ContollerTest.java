package com.multicampus.hhh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContollerTest {

    @GetMapping("/test")
    public String HelloWord(){
        return "hello";
    }

    @GetMapping("/member/signup")
    public String test(){
        return "/member/signup";
    }
}
