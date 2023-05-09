package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
public class AuthLoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/social")
    public String socialGET(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        MemberVO memberVO = memberService.findMember(userId);
        if(memberVO.getNickname() != null){
//            model.addAttribute("socialId", memberVO);
            session.setAttribute("loginId", memberVO);
            return "redirect:/";
        }
        log.info("소셜 로그인 아이디 추가 아이디 확인" + userId + "============================================================");
        model.addAttribute("userId", userId);

        return "/member/socialform";
    }

    // 소셜 로그인 후처리
    @PostMapping("/social")
    public String socialPOST(@RequestParam("userId") String userId, @RequestParam("nickname") String nickname,
                             @RequestParam("address") String address, @RequestParam("phnum") String phnum, Model model, HttpSession session){
        log.info("소셜 로그인 아이디 확인: " + userId);
        MemberVO member = memberService.findMember(userId);
        member.setNickname(nickname);
        member.setAddress(address);
        member.setPhnum(phnum);
        log.info("소셜 로그인 입력 전");
        memberService.socialInsert(member);
        log.info("소셜 로그인 추가 입력 완료");
//        model.addAttribute("socialId", member);
        session.setAttribute("loginId", member);

        return "redirect:/";
    }
}
