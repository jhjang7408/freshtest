package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String register(MemberDTO memberDTO, @RequestParam("address_detail") String addressDetail, Model model){
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
        String address = memberDTO.getAddress() + " " + addressDetail;
        memberDTO.setAddress(address);
        log.info("주소 저장");
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
    public String findpassGET(HttpSession session, Model model) {
        log.info("비밀번호 찾기 페이지");
        if (session.getAttribute("fixMsg") != null) {
            model.addAttribute("fixMsg", session.getAttribute("fixMsg"));
            session.removeAttribute("fixMsg");
        }
        return "member/findpassword";
    }

    @PostMapping("/emailAuth")  //비밀번호 변경 1
    public String emailAuth(@RequestParam("userid") String id, @RequestParam("useremail") String email, HttpSession session,Model model) {
        log.info("컨트롤러 이메일 검증 메서드");
        String key = memberService.memberEmail(id, email);
        log.info("인증키값 : " + key);
        if (key.equals("abc")) {
            session.setAttribute("fixMsg", "입력한 정보가 맞지 않습니다.");
            return "redirect:/member/findpassword";
        } else {
            session.setAttribute("key", key);
            model.addAttribute("userid", id);
            return "/member/findpassword2";
        }
    }

    @PostMapping("/emailAuthCode")  //비밀번호 변경2
    public String emailAuthCode(@RequestParam("userid") String userid, @RequestParam("authCode") String authCode, HttpSession session, Model model) {
        String key = (String) session.getAttribute("key");
        if(key.equals(authCode)){
            session.removeAttribute("key");
            model.addAttribute("userid",userid);
            return "/member/findpassword3";
        } else {
            model.addAttribute("fixMsg", "잘못된 인증번호입니다.");
            model.addAttribute("userid",userid);
            return "/member/findpassword2";
        }
    }

    @PostMapping("/changepassword") //비밀번호 변경3
    public String changepassword(@RequestParam("userid") String userid, @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword, Model model, RedirectAttributes redirectAttributes){

        if(!newPassword.equals(confirmPassword)){
            model.addAttribute("fixMsg","비밀번호가 일치하지 않습니다.");
            model.addAttribute("userid", userid);
            return "/member/findpassword3";
        } else {
            memberService.changePass(userid, newPassword);
            redirectAttributes.addFlashAttribute("fixMsg","비밀번호가 변경되었습니다.");
            return "redirect:/member/signin";
        }
    }



//    @PostMapping("findpassword")
//    public String findpassPOST(@RequestParam("userid") String userid, @RequestParam("email") String email) {
//        String checkNum="";
//        String checkmail = memberService.findMember(userid).getEmail();
//        if(checkmail.equals(email)) {
//            checkNum = memberService.mailSend(email);
//        } else {
////            return "redirect:/member/findpassword";
//            log.info("이메일 인증코드를 보내지 못했습니다.");
//        }
//
//        // checkNum을 html로 보내서 인증번호를 확인해야한다.
//        //PostMapping 을 하나 더 만들어서 인증번호 확인
//
////        return "/member/signin";
//        return checkNum;
//    }

//    @PostMapping("checkCode")
//    public String checkCode(@RequestParam("mailCode") String code){
//
//        boolean abc = memberService.checkCode(code);
//
//        if(abc){
//            return "/"
//        } else {
//
//        }
//
//    }



    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
