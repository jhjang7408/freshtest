package com.multicampus.hhh.controller;

import com.multicampus.hhh.service.QaBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.internal.bytebuddy.build.BuildLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qaboards")
@RequiredArgsConstructor
@Log4j2
public class QaBoardController {

    private final QaBoardService qaBoardService;

    @GetMapping("/qalist")
    public String main(Model model){
        model.addAttribute("qalist", qaBoardService.qaBoardList());

        return "/qa/qalist";
    }


    @GetMapping("/qaview")
    public String qaview(int qaid, Model model){

        log.info(qaBoardService.findById(qaid));

        model.addAttribute("qaview", qaBoardService.findById(qaid));
        return "/qa/qaview";
    }


}
