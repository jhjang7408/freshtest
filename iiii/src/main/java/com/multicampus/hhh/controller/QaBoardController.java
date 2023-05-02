package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.service.QaBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/qamodify")
    public String qamodifyForm(@RequestParam(name = "qa_id") int qaid, Model model){


            QaBoard findqaid = qaBoardService.findById(qaid);
            model.addAttribute("qamodify", findqaid);


            return "/qa/qamodify";

    }


    @PostMapping(value = "/qamodify")
    public String qamodify(QaBoard qaBoard){


        log.info(qaBoard.getQaid());
        qaBoardService.qamodify(qaBoard);

        return "redirect:/qa/qaview?qa_id=" + qaBoard.getQaid();
    }


    @PostMapping("/qadelete")
    public String qadelete(QaBoard qaBoard){

        int qaid = qaBoard.getQaid();
        log.info(qaid);
        qaBoardService.qadelete(qaid);

        return "redirect:/qa/qalist";
    }


    @GetMapping("/guide")
    public String qaguide(){
        return "/qa/guide";
    }


    @GetMapping("/bikemanagement")
    public String bikemanagement(){
        return "/qa/bikemanagement";
    }


    @GetMapping("/safe")
    public String qasafe(){
        return "/qa/safe";
    }



}
