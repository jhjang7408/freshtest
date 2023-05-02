package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    MemberService memberService;


    @GetMapping("/orderlist-bike")
    public void orderbikelist(){
        log.info("orderlistpage");
    }

    @GetMapping("/orderlist-acc")
    public void orderacclist(){
        log.info("orderaccListpage");
    }
    
    @GetMapping("/account-modify")
    public void accountmod(HttpSession session, Model model){
        log.info("개인정보수정 페이지");
    }

    @PostMapping("/account-modify")
    public String modifyInfo(HttpSession session, @ModelAttribute MemberVO memberVO){
        memberService.modifyMember(memberVO);
        log.info("개인정보수정 테스트중");
        log.info(memberVO);
        return "redirect:/mypage/account-modify";
    }
    
    @GetMapping("/listsell")
    public void listsell(){
        log.info("판매내역");
    }
    
    @GetMapping("/shop-cart")
    public  void cartPage(){
        log.info("장바구니");
    }

    @PostMapping("/changepass")
    public String changepassword(HttpSession session, @RequestParam("password") String password){
        MemberVO memberVO= (MemberVO) session.getAttribute("loginId");
        log.info(memberVO);
        log.info(password);
        memberVO.setPassword(password);
        log.info("비번 변경3");
        memberService.modifyMember(memberVO);
        log.info("비밀번호 변경 완료");

        return "mypage/account-modify";
    }

    @PostMapping("/deleteMember")
    public String remove(HttpSession session){
        MemberVO memberVO= (MemberVO) session.getAttribute("loginId");
        log.info(memberVO);
        memberService.removeMember(memberVO);
        session.invalidate();
        log.info("회원탈퇴 확인");

        return "redirect:/";
    }
}
