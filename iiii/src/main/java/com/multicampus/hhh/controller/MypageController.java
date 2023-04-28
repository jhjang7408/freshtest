package com.multicampus.hhh.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping("/orderlist-bike")
    public void orderbikelist(){
        log.info("orderlistpage");
    }

    @GetMapping("/orderlist-acc")
    public void orderacclist(){
        log.info("orderaccListpage");
    }
    
    @GetMapping("/account-modify")
    public void accountmod(){
        log.info("개인정보수정 페이지");
    }
    
    @GetMapping("/listsell")
    public void listsell(){
        log.info("판매내역");
    }

}
