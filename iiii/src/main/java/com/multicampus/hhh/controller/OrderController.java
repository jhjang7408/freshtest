package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.OrderDTO;
import com.multicampus.hhh.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    @PostMapping("/orders/register")
    public String OrderData(OrderDTO orderDTO, RedirectAttributes redirectAttributes, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO memberVO = ((PrincipalDetails) authentication.getPrincipal()).getMemberVO();

        orderDTO.setUserid(memberVO.getUserid());

        service.register(orderDTO);
        redirectAttributes.addFlashAttribute("order", orderDTO);
        return "redirect:/acc/payment?acid=" + orderDTO.getAcid();
    }

    @GetMapping("/acc/showpayment")
    public String showPaymentPage(@RequestParam("acid") String acid, Model model, @ModelAttribute("order") OrderDTO orderDTO) {
        if (orderDTO == null || orderDTO.getAcid() == 0 || !Integer.toString(orderDTO.getAcid()).equals(acid)) {
            orderDTO = service.findOrderDTOByAcid(acid);
        }
        if (orderDTO != null) {
            model.addAttribute("order", orderDTO);
        }
        return "acc/payment";
    }

    @GetMapping("/mypage/orderlist-acc")
    public String OrderList(Model model, Principal principal){
        String userid = principal.getName();
        List<OrderDTO> orders = service.getOrdersByUserId(userid);
        model.addAttribute("orders", orders);
        return "/mypage/orderlist-acc";
    }
}
