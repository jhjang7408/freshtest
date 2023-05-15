package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.*;
import com.multicampus.hhh.service.MemberService;
import com.multicampus.hhh.service.PageMoonService;
import com.multicampus.hhh.service.QaMoonBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.multicampus.hhh.domain.MemberRole.ADMIN;
import static com.multicampus.hhh.domain.MemberRole.USER;

@Controller
@RequestMapping("/moon")
@RequiredArgsConstructor
@Log4j2
public class QaMoonController {

    private final QaMoonBoardService qaMoonBoardService;
    private final MemberService memberService;
    private final PageMoonService pageMoonService;

    @GetMapping("/moonlist")    //이건 자주 묻는 질문 html 연결한거임
    public String moonlist(){
        return "/moon/moonlist";
    }



    /*@GetMapping("/qamoonlist")
    public String qamoonList(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO memberVO = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();

       // MemberVO memberVO = (MemberVO) session.getAttribute("loginId"); // 로그인한 아이디 가져오기
        log.info(memberVO + "ssssssssssssssssssssssssssssssss");


        String userid= memberVO.getUserid();

        log.info(memberService.findRole(userid)+"<<<<<이것이 바로 권한인가");

        if(memberService.findRole(userid) == USER) {

            List<QaMoonBoard> qaMoonList = qaMoonBoardService.findmoonById(userid); // 아이디에 해당하는 게시글 가져오기

            log.info(qaMoonList + "dddddddddddddddddddddddddddddddddddddddd");
            model.addAttribute("qamoonlist", qaMoonList); //
            return "/moon/qamoonlist"; //
        } else if(memberService.findRole(userid) == ADMIN){
            List<QaMoonBoard> qaMoonList = qaMoonBoardService.findmoonAll(); // 아이디에 해당하는 게시글 가져오기

            log.info(qaMoonList + "dddddddddddddddddddddddddddddddddddddddd");
            model.addAttribute("qamoonlist", qaMoonList); //
            return "/moon/qamoonlist"; //
        } else {
            return "/moon/moonlist";
        }
    }*/




    @GetMapping("/qamoonlist")
    public String qamoonList(PagingMoonVO vo, Model model
                             ,PagingVO vvo

            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO memberVO = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();

        // MemberVO memberVO = (MemberVO) session.getAttribute("loginId"); // 로그인한 아이디 가져오기
        log.info(memberVO + "ssssssssssssssssssssssssssssssss");


        String userid= memberVO.getUserid();

        log.info(memberService.findRole(userid)+"<<<<<이것이 바로 권한인가");

        if(memberService.findRole(userid) == USER) {
            /*List<QaMoonBoard> qaMoonList = qaMoonBoardService.findmoonById(userid); // 아이디에 해당하는 게시글 가져오기

            log.info(qaMoonList + "dddddddddddddddddddddddddddddddddddddddd");*/

            int total = pageMoonService.countMoonBoard(userid);


            if (nowPage == null && cntPerPage == null) {
                nowPage = "1";
                cntPerPage = "5";
            } else if (nowPage == null) {
                nowPage = "1";
            } else if (cntPerPage == null) {
                cntPerPage = "5";
            }
            vo = new PagingMoonVO(userid, total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
            model.addAttribute("pagingMoon", vo);
            model.addAttribute("qamoonlist", pageMoonService.selectMoonBoard(vo));


            /*model.addAttribute("qamoonlist", qaMoonList); //*/
            return "/moon/qamoonlist"; //


        } else if(memberService.findRole(userid) == ADMIN){
            int total = pageMoonService.countMoonBoardAll();
            if (nowPage == null && cntPerPage == null) {
                nowPage = "1";
                cntPerPage = "5";
            } else if (nowPage == null) {
                nowPage = "1";
            } else if (cntPerPage == null) {
                cntPerPage = "5";
            }
            vvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
            model.addAttribute("pagingMoon", vvo);
            model.addAttribute("qamoonlist", pageMoonService.selectMoonBoardAll(vvo));

            return "/moon/qamoonlist";

        } else {
            return "/moon/moonlist";



        }

    }





































    @GetMapping("/qamoonview")
    public String qamoonview(@RequestParam(name = "moon_id") int moonid, Model model){

        QaMoonBoard findmoonid = qaMoonBoardService.findById(moonid);

        model.addAttribute("qamoonview", findmoonid);
        model.addAttribute("qamoonreply", qaMoonBoardService.qaMoonBoardReplyList(moonid));
        log.info(findmoonid.getContent());

        return "/moon/qamoonview";
    }



    @GetMapping("/qamoonregister")
    public String qamoonregisterForm(){
        return "/moon/qamoonregister";
    }


    @PostMapping("/qamoonregister")
    public String qamoonregister(QaMoonBoard qaMoonBoard){
        qaMoonBoardService.qamoonregister(qaMoonBoard);

        return "redirect:/moon/qamoonlist";
    }




    @GetMapping("/qamoonmodify")
    public String qamoonmodifyForm(@RequestParam(name = "moon_id") int moonid, Model model){

        QaMoonBoard findmoonid = qaMoonBoardService.findById(moonid);
        model.addAttribute("qamoonmodify", findmoonid);

        return "/moon/qamoonmodify";
    }


    @PostMapping("/qamoonmodify")
    public String qamodify(QaMoonBoard qaMoonBoard){
        qaMoonBoardService.qamoonmodify(qaMoonBoard);

        return "redirect:/moon/qamoonview?moon_id="+qaMoonBoard.getMoonid();
    }


    @PostMapping("/qamoondelete")
    public String qamoondelete(QaMoonBoard qaMoonBoard){
        int moonid = qaMoonBoard.getMoonid();

        qaMoonBoardService.qamoondelete(moonid);

        return "redirect:/moon/qamoonlist";
    }


    @PostMapping("/qamoonreplydelete")
    public String qamoonreplydelete(QaMoonBoard qaMoonBoard){
        int moonid = qaMoonBoard.getMoonid();
        qaMoonBoardService.qamoonreplydelete(moonid);

        return "redirect:/moon/qamoonlist";
    }


    @PostMapping("/qamoonreplyregister")
    public String  qamoonregister(QaMoonBoardReply qaMoonBoardReply){

        qaMoonBoardService.qamoonreplyregister(qaMoonBoardReply);

        return "redirect:/moon/qamoonview?moon_id=" + qaMoonBoardReply.getMoonid();
    }



    @PostMapping("/qamoonreplydeleteone")
    public String qamoonreplydeleteone(QaMoonBoardReply qaMoonBoardReply){
        int moonreplyid = qaMoonBoardReply.getMoonreplyid();
        qaMoonBoardService.qamoonreplydeleteone(moonreplyid);

        return "redirect:/moon/qamoonlist";
    }



}
