package com.multicampus.hhh.controller;

import com.multicampus.hhh.service.BikeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class BikeBoardReplyController {

    private final BikeBoardService bikeBoardService;


}
