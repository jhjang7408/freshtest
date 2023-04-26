package com.multicampus.hhh.controller;

import com.multicampus.hhh.dto.qaBoardDTO;
import com.multicampus.hhh.mapper.qaBoardMapper;
import com.multicampus.hhh.service.qaBoardService;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/qa")
@Log4j2
@RequiredArgsConstructor
public class qaBoardController {

    @Autowired
    qaBoardService qaboardService;


    @GetMapping("/qaregister")
    public void registerGET(){};

    @PostMapping("/qaregister")
    public String registerPost(qaBoardDTO qaboardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/qa/qaregister";
        }
        return "redirect:/qa/qalist";
    }



    @GetMapping({"/qaread", "/qamodify"})
    public void read(int qa_id, Model model){
        qaBoardDTO qaboardDTO = qaboardService.getOne(qa_id);

        model.addAttribute("dto", qaboardDTO);
    }


    @PostMapping("/qaremove")
    public String remove(int qa_id, RedirectAttributes redirectAttributes){

        qaboardService.remove(qa_id);

        return "redirect:/qa/qalist";
    }


    @PostMapping("/qamodify")
    public String modify( qaBoardDTO qaboardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("qa_id", qaboardDTO.getQa_id());
            return "redirect:/qa/qamodify";
        }


        qaboardService.modify(qaboardDTO);

        return "redirect:/qa/qamodify";
    }


}
