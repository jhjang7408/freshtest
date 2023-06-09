package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.*;
import com.multicampus.hhh.service.PageReplyService;
import com.multicampus.hhh.service.PageService;
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
    private final PageService pageService;
    private final PageReplyService pageReplyService;

    @GetMapping("/qalist")
    public String main(PagingVO vo, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage){

        int total = pageService.countBoard();
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "5";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "5";
        }
        vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
        model.addAttribute("paging", vo);
        model.addAttribute("viewAll", pageService.selectBoard(vo));

//        model.addAttribute("qalist", qaBoardService.qaBoardList());

        return "/qa/qalist";
    }




    /*@GetMapping("/qaview")
    public String qaview(@RequestParam(name = "qa_id") int qaid, Model model){

        QaBoard findqaid = qaBoardService.findById(qaid);

        model.addAttribute("qaview", findqaid);

        model.addAttribute("qareply", qaBoardService.qaBoardReplyList(qaid));

        return "/qa/qaview";
    }
*/

    @GetMapping("/qaview")
    public String qaview(@RequestParam(name = "qa_id") int qaid, Model model,
                         PagingReplyVO vo,
                         @RequestParam(value = "nowPage", required = false) String nowPage,
                         @RequestParam(value = "cntPerPage", required = false) String cntPerPage) {

        QaBoard findqaid = qaBoardService.findById(qaid);

        model.addAttribute("qaview", findqaid);


        int total = pageReplyService.countReplyBoard(qaid);
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "3";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "3";
        }
        vo = new PagingReplyVO(qaid, total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
        model.addAttribute("pagingReply", vo);
        model.addAttribute("viewReplyAll", pageReplyService.selectReplyBoard(vo));

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
