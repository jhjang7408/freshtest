package com.multicampus.hhh.controller;


import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.service.BikeBoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeBoardReplyController {

    private final BikeBoardReplyService bikeBoardReplyService;

    @PostMapping("productSingle/{bikeid}/bikereply")
    public String register(@PathVariable int bikeid, BikeBoardDTO bikeBoardDTO, BikeBoardReplyDTO bikeBoardReplyDTO, Model model, HttpSession session){
        int bikereplyid = bikeBoardReplyDTO.getBikereplyid();
        MemberVO userid = (MemberVO) session.getAttribute("loginId");
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

//    @PostMapping()
//    public String modify(){
//        return "redirect:/bike/productSingle/{bikeid}";
//    }
//
//    @PostMapping()
//    public String delete(){
//        return "redirect:/bike/productSingle/{bikeid}";
//    }
}
