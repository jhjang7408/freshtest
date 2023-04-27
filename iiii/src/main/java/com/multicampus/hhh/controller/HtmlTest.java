package com.multicampus.hhh.controller;

import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qa")
@Log4j2
@RequiredArgsConstructor
public class HtmlTest {

    @GetMapping("/test1")
    public void list() {
    }
}
