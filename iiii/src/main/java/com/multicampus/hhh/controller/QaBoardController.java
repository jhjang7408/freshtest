package com.multicampus.hhh.controller;

import com.multicampus.hhh.service.QaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qaboards")
@RequiredArgsConstructor
public class QaBoardController {

    private final QaBoardService qaBoardService;

    @GetMapping("/qalist")
    public String qalist(Model model){
        model.addAttribute("qalist", qaBoardService.qaBoardList());

        return "/qa/qalist";
    }


    @GetMapping("/read")
    public String qaboard(@PathVariable int qaid, Model model){
        model.addAttribute("qaboard", qaBoardService.findById(qaid));

        return "/qa/qaboard";
    }


}
