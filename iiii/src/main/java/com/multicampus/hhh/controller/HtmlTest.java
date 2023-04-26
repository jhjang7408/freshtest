package com.multicampus.hhh.controller;

import com.multicampus.hhh.dto.qaPageRequestDTO;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/qa")
@Log4j2
@RequiredArgsConstructor
public class HtmlTest {

    @GetMapping("/test1")
    public void list() {
    }
}
