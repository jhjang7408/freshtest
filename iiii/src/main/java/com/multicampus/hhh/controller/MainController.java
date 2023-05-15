package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.service.AccBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
public class MainController {

    @Autowired
    private AccBoardService accBoardService;

    @GetMapping("/")
    public String main(Model model){
        List<AccBoardDTO> list = accBoardService.listTen();
        model.addAttribute("list", list);
        log.info("메인페이지");
        return "index";
    }

    @GetMapping("/search")
    public void search(@RequestParam String data){

    }
}
