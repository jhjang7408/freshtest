package com.multicampus.hhh.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/member/signup")
    public String signup(){

        log.info("===========GET signup===========");

        return "/member/signup";
    }

}
