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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/qa")
@RequiredArgsConstructor
@Log4j2
public class QaBoardController {

    private final QaBoardService qaBoardService;

    @GetMapping("/qalist")
    public String main(Model model){
        model.addAttribute("qalist", qaBoardService.qaBoardList());

        return "qa/qalist";
    }


    @GetMapping("/qaview")
    public String qaview(@RequestParam(name = "qa_id") Integer qaid, Model model){

        log.info(qaBoardService.findById(qaid));

        model.addAttribute("qaview", qaBoardService.findById(qaid));

        return "qa/qaview";
    }


}
