package com.multicampus.hhh.controller;


import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.service.BikeBoardReplyService;
import com.multicampus.hhh.service.BikeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeBoardReplyController {
    private final BikeBoardService service;
    private final BikeBoardReplyService bikeBoardReplyService;
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("productSingle/{bikeid}/bikereply")
    public String register(@PathVariable int bikeid, BikeBoardDTO bikeBoardDTO, BikeBoardReplyDTO bikeBoardReplyDTO, Model model, HttpSession session){
        int bikereplyid = bikeBoardReplyDTO.getBikereplyid();

        //MemberVO userid = (MemberVO) session.getAttribute("loginId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO userid = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
        //'user_id' cannot be null오류 발생해서
        if (userid != null) {
            bikeBoardReplyDTO.setUserid(userid.getUserid());
        } else {
            // 로그인이 되어 있지 않은 경우
            return "redirect:/member/signin";
        }
        ;
        model.addAttribute("reply", bikeBoardReplyDTO);
        model.addAttribute("bike", bikeBoardDTO);
        bikeBoardReplyService.register(bikeBoardReplyDTO);

        return "redirect:/bike/productSingle/" + bikeid;
    }

//    @PostMapping("productSingle/{bikeid}/{bikereplyid}/update/")
//    public String modify(@PathVariable int bikeid, @PathVariable int bikereplyid){
//
//        return "redirect:/bike/productSingle/" + bikeid;
//    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("productSingle/{bikeid}/{bikereplyid}/delete/")
    public String delete(@PathVariable int bikeid, @PathVariable int bikereplyid, Principal principal, Model model){

        BikeBoardDTO bikeBoardDTO = service.readOne(bikeid);
        if (principal != null) {
            String loginId = principal.getName();
            model.addAttribute("loginId", loginId);
        }

        // writer 추가
        String writer = bikeBoardDTO.getUserid();
        model.addAttribute("writer", writer);
        bikeBoardReplyService.delete(bikereplyid);
        return "redirect:/bike/productSingle/" + bikeid;
    }
}
