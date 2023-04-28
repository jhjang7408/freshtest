package com.multicampus.hhh.controller;

import com.multicampus.hhh.dto.BikeBoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Log4j2
public class BikeBoardControllerTests {

    @Test
    @PostMapping("/sell/productRegister")
    public String registerPost(@Valid BikeBoardDTO bikeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/sell/productRegister";
        }
        log.info(bikeBoardDTO);
        return "redirect:/sell/productRegister";
    }
}
