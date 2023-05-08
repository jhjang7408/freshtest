package com.multicampus.hhh.controller;


import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.service.BikeBoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeBoardReplyController {

    private final BikeBoardReplyService bikeBoardReplyService;

    @PostMapping("productSingle/{bikeId}/bikereply")
    public String register(@PathVariable int bikeid, BikeBoardDTO bikeBoardDTO, BikeBoardReplyDTO bikeBoardReplyDTO, Model model){
        int bikereplyid = bikeBoardReplyDTO.getBikereplyid();
        model.addAttribute("reply", bikeBoardReplyDTO);
        model.addAttribute("bike", bikeBoardDTO);
        bikeBoardReplyService.register(bikeBoardReplyDTO);

        return "bike/productSingle/" +bikeid;
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
