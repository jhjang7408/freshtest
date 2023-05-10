package com.multicampus.hhh.controller;

import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.service.AccBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequestMapping("/acc")
@RequiredArgsConstructor
public class AccBoardController {

    private final AccBoardService service;

    @GetMapping("/accList")
    public void list(Model model){
        log.info("악세서리 구매게시판");
        model.addAttribute("accList", service.getAll());
    }

    @GetMapping("/productRegister")
    public void registerGET() {
        log.info("GET acc register.......");
    }



    @PostMapping("/productRegister")
    public String registerPost(@Valid AccBoardDTO accBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        log.info("악세서리 등록");


        service.register(accBoardDTO);

        return "redirect:/acc/accList";
    }
    @GetMapping("productSingle")
    public void readOne(){

    }
    @GetMapping("payment")
    public void pay(){

    }

}
