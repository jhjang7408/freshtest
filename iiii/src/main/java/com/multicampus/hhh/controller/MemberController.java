package com.multicampus.hhh.controller;

import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.mapper.MemberMapper;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/signup")
    public String signup(Model model){
        log.info("===========GET signup===========");
        model.addAttribute("memberDTO",new MemberDTO());

        return "member/signup";

    }

    @PostMapping("/signup")
    public String register(MemberDTO memberDTO, Model model){
        log.info("======POST signup =======");

        memberService.saveMember(memberDTO);

        return "redirect:/member/login";
    }

    @GetMapping("/signin")
    public String loginpage(){
        log.info("로그인 페이지");
        return "member/signin";
    }

    @PostMapping("/signin")
    public String login(){
        log.info("로그인 성공");
        return "redirect:/index";
    }
}
