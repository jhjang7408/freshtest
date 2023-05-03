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
        log.info("GET bike register.......");
    }



    @PostMapping("/productRegister")
    public String registerPost(@Valid AccBoardDTO accBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        log.info("자전거 등록");


        service.register(accBoardDTO);

        return "redirect:/acc/accList";
    }

//    @GetMapping("/list")
//    public void list(Model model)
//    {
//        log.info("list");
//        model.addAttribute("list",service.list());
//    }
//
//    @PostMapping("/register")
//    public String register(AccBoardDTO accBoardDTO, RedirectAttributes rttr){
//
//        log.info("register: " + accBoardDTO);
//        service.register(accBoardDTO);
//        rttr.addFlashAttribute("result", accBoardDTO.get(Ac_id));
//
//        return "redirect:/shop/list";
//    }
//
//    @GetMapping("/read")
//    public void readOne(@RequestParam("ac_id") Long ac_id, Model model){
//
//        log.info("/read");
//        model.addAttribute("AccBoard",service.readOne(ac_id));
//    }
//
//
//    @PostMapping("modify")
//    public String modify(AccBoardDTO accBoardDTO, RedirectAttributes rttr){
//        log.info("modify: " + accBoardDTO);
//
//        if(service.modify(accBoardDTO)){
//            rttr.addFlashAttribute("result","sucess");
//        }
//        return "redirect:/shop/list";
//    }
//
//    @PostMapping("/remove")
//    public String remove(@RequestParam("ac_id")Long ac_id , RedirectAttributes rttr){
//        log.info("remove..." + ac_id);
//        if(service.remove(ac_id)){
//            rttr.addFlashAttribute("result", "sucess");
//        }
//        return "redirect:/shop/list";
//    }

}
