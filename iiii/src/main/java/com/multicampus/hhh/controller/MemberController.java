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

import javax.servlet.http.HttpSession;
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
        MemberVO check = memberService.findMember(memberDTO.getUserid());
        if(check != null){
            model.addAttribute("errorMsg","이미 등록된 아이디 입니다.");
            return "member/signup";
        }
        memberService.saveMember(memberDTO);
        log.info("컨트롤러 회원가입 체크");

        return "redirect:/member/signin";
    }

    @GetMapping("/signin")
    public String loginpage(@ModelAttribute("loginMember") MemberDTO memberDTO){
        log.info("로그인 페이지");
//        String id = (String) session.getAttribute("userid");
//        if(id != null){ //로그인 상태
//            MemberVO memberVO = memberService.findMember(id);
//            model.addAttribute("Member",memberVO);
//            return "redirect:/";
//        }

        return "member/signin";
    }

    @PostMapping("/signin")
    public String login(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult,HttpSession session) throws Exception{

        if(bindingResult.hasErrors()){
            log.info("binding오류");
            return "redirect:/member/signin";
        }
        MemberVO memberVO= memberService.checkMember(memberDTO.getUserid(), memberDTO.getPassword());
        log.info(memberVO);

        if(memberVO == null){
            bindingResult.reject("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("로그인 오류");
            return "redirect:/member/signin";
        }
        session.setAttribute("loginId",memberVO);
        log.info("로그인 세션 부여");

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        log.info("로그아웃");

        return "redirect:/";
    }
    
    @GetMapping("/findpassword")
    public void findpass(){
        log.info("비밀번호 찾기 페이지");
    }


}
