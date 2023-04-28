package com.multicampus.hhh.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class MainController {

    @GetMapping("/index")
    public String main(){
        log.info("main");
        return "/index";
    }
}
