package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.service.QaBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.internal.bytebuddy.build.BuildLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/qa")
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
    public String qaview(@RequestParam(name = "qa_id") int qaid, Model model){

        QaBoard findqaid = qaBoardService.findById(qaid);

        model.addAttribute("qaview", findqaid);
        return "/qa/qaview";
    }



    @GetMapping("/qaregister")
    public String qaregisterForm(){
        return "/qa/qaregister";
    }


    @PostMapping("/qaregister")
    public String qaregister(QaBoard qaBoard){
        qaBoardService.qaregister(qaBoard);

        return "redirect:/qa/qalist";
    }



}
