package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.mapper.MemberMapper;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

//        try{
//            memberService.findMember(memberDTO.getUser_id());
//            memberService.saveMember(memberDTO);
//        } catch (Exception e){
//            model.addAttribute("errorMsg","이미 등록된 아이디 입니다.");
//                    return "member/signin";
//        }
        int check = memberService.findMember(memberDTO.getUser_id());
        if(check == 1){
            model.addAttribute("errorMsg","이미 등록된 아이디 입니다.");
            return "member/signup";
        }
        memberService.saveMember(memberDTO);

        return "redirect:/member/login";
    }

    @GetMapping("/signin")
    public String loginpage(@ModelAttribute("loginMember") MemberDTO memberDTO){
        log.info("로그인 페이지");
        return "member/signin";
    }

    @PostMapping("/signin")
    public String login(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/signin";
        }
        log.info("POST 로그인 첫번째");
        MemberVO memberVO= memberService.checkMember(memberDTO.getUser_id(), memberDTO.getPassword());
        
        log.info("memberService 동작");
        if(memberVO == null){
            bindingResult.reject("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/signin";
        }
        log.info("POST 로그인 두번째");


        return "redirect:/";
    }
    
    @GetMapping("/findpassword")
    public void findpass(){
        log.info("비밀번호 찾기 페이지");
    }


}
