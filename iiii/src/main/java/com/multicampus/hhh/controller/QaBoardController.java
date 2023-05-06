package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.domain.QaBoardReply;
import com.multicampus.hhh.service.QaBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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



 /*   @GetMapping("/qaview")
    public String qaview(@RequestParam(name = "qa_id") int qaid, Model model){

        QaBoard findqaid = qaBoardService.findById(qaid);

        model.addAttribute("qaview", findqaid);
        return "/qa/qaview";
    }

*/


    @GetMapping("/qaview")
    public String qaview(@RequestParam(name = "qa_id") int qaid, Model model){

        QaBoard findqaid = qaBoardService.findById(qaid);


        model.addAttribute("qaview", findqaid);
        model.addAttribute("qareply", qaBoardService.qaBoardReplyList(qaid));

        return "/qa/qaview";
    }


    @PostMapping("/qareplyregister")
    public String qareplyRegister(QaBoardReply qaBoardReply){

        qaBoardService.qareplyregister(qaBoardReply);

        return "redirect:/qa/qaview?qa_id=" + qaBoardReply.getQaid();
    }



   /* @PostMapping("/qareplyregister")
    public String qareplyRegister(QaBoardReply qaBoardReply, HttpSession session){
        MemberVO memberVO = (MemberVO) session.getAttribute("loginid");
        String userid = memberVO.getUserid();
        log.info("asdfasdfasdfasdfasdf");
        qaBoardReply.setUserid(userid);
        log.info(userid);
        qaBoardService.qareplyregister(qaBoardReply);

        return "redirect:/qa/qaview?qa_id=" + qaBoardReply.getQaid();
    }*/















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


    /*@PostMapping("/qareplymodify")
    public String qareplymodify(QaBoardReply qaBoardReply){

        int qareplyid = qaBoardReply.getQareplyid();
        qaBoardService.qareplymodify(qareplyid);

        return "redirect:/qa/qaview?qa_id=" + qaBoardReply.getQaid();
    }*/









    @PostMapping("/qadelete")
    public String qadelete(QaBoard qaBoard){

        int qaid = qaBoard.getQaid();
        log.info(qaid);
        qaBoardService.qadelete(qaid);

        return "redirect:/qa/qalist";
    }


    @PostMapping("/qareplydelete")
    public String qareplydelete(QaBoard qaBoard){

        int qaid = qaBoard.getQaid();
        log.info(qaid);
        qaBoardService.qareplydelete(qaid);

        return "redirect:/qa/qalist";
    }




    @PostMapping("/qareplydeleteone")
    public String qareplydeleteone(QaBoardReply qaBoardReply){

        int qareplyid = qaBoardReply.getQareplyid();
        log.info(qareplyid);
        qaBoardService.qareplydeleteone(qareplyid);

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
