package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/orderlist-bike")
    public void orderbikelist(HttpSession session, Model model){

        log.info("자전거 구매내역");
    }

    @GetMapping("/orderlist-acc")
    public void orderacclist(){
        log.info("악세사리 구매내역");
    }

    @GetMapping("/account-modify")
    public void accountmod(Model model,HttpSession session){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//
//        model.addAttribute("userId", userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO memberVO = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
        log.info("로그인 아이디 ROLE 확인 =====================" + memberVO.getMemberRole().name());

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        log.info("로그인 아이디 권한 확인 =====================" + authorities);

        Collection<? extends GrantedAuthority> abc =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        log.info("현재 로그인 아이디 권한들 목록");
        for (GrantedAuthority authority : abc) {
            System.out.println(authority.getAuthority());
        }

        log.info("개인정보수정 페이지");
    }


    @PostMapping("/account-modify")     //개인정보 수정
    public String modifyInfo(HttpSession session, @ModelAttribute MemberVO memberVO, @RequestParam("address_detail") String addressd){

        memberService.modifyMember(memberVO);
        log.info("개인정보수정 테스트중");
        log.info(memberVO);
        return "redirect:/mypage/account-modify";
    }


    @GetMapping("/listsell")
    public String listsell(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO memberVO = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();

        String userid = memberVO.getUserid();
        List<BikeBoardVO> bikeBoard = memberService.findbike(userid);
        model.addAttribute("bikelist", bikeBoard);
        log.info("판매내역");
        return "/mypage/listsell";
    }












    @GetMapping("/shop-cart")
    public  void cartPage(){
        log.info("장바구니");
    }

    @PostMapping("/changepass")     //비밀번호 변경
    public String changepassword(@RequestParam("userid") String userid, @RequestParam("password") String password, Model model){
        log.info("비밀번호 변경 아이디 확인" + userid);
        MemberVO memberVO = memberService.findMember(userid);
//        MemberVO memberVO= (MemberVO) session.getAttribute("loginId");
//        log.info(memberVO);
        log.info(password);
        String newPassword = bCryptPasswordEncoder.encode(password);
        memberVO.setPassword(newPassword);
//        memberVO.setPassword(password);
        log.info("비번 변경3");
        memberService.modifyPassMember(memberVO);
        log.info("비밀번호 변경 완료");

        return "mypage/account-modify";
    }

    @PostMapping("/deleteMember")
    public String remove(HttpSession session){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userid = ((PrincipalDetails) authentication.getPrincipal()).getMemberVO().getUserid();
        log.info("회원탈퇴 아이디 확인중 ======================" + userid);

        MemberVO memberVO = memberService.findMember(userid);
//        MemberVO memberVO= (MemberVO) session.getAttribute("loginId");
        log.info(memberVO);
        memberService.removeMember(memberVO);
        session.invalidate();
        log.info("회원탈퇴 확인");

        return "redirect:/";
    }
}
