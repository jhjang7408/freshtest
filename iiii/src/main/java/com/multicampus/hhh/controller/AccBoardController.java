package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.service.AccBoardService;
import com.multicampus.hhh.service.MemberService;
import com.multicampus.hhh.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/acc")
@RequiredArgsConstructor
public class AccBoardController {

    private final AccBoardService service;
    private final MemberService memberService;
    private final PageService pageService;

    @GetMapping("/accList")
    public String list(Model model, PagingVO vo,@RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage){

        int total = pageService.countAccBoard();
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "5";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "5";
        }
        vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
        model.addAttribute("paging", vo);
        model.addAttribute("viewAll", pageService.selectAccBoard(vo));

        return "acc/accList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/productRegister")
    public String registerGET(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO userid = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
        //로그인 하지 않았을 경우 로그인 페이지로 이동
        if(userid == null){
            log.info("로그인 하지 않았을 경우 로그인 화면으로 이동");
            return "redirect:/member/signin";
        }
        model.addAttribute("userid", userid.getUserid());
        log.info("로그인 확인되어 등록페이지로 이동");
        return "acc/productRegister";
    }


    @Secured("ROLE_ADMIN")
    @PostMapping("/productRegister")
    public String registerPost(@Valid AccBoardDTO accBoardDTO,@RequestParam("imgFile") MultipartFile imgFile) throws IOException{



        log.info("악세서리 등록");


        if (imgFile != null && !imgFile.isEmpty()) {
            // 업로드 한 이미지 파일명 저장
            String imageName = imgFile.getOriginalFilename();
            String savedName = UUID.randomUUID().toString() + imageName;
            //이미지 파일 저장할 저장공간 설정
            String absolutePath = System.getProperty("user.dir");
            String path = absolutePath + "/src/main/resources/static/uploadImg";
            File file = new File(path, savedName);
            imgFile.transferTo(file);
            // 이미지 파일명을 DTO에 설정
            accBoardDTO.setImage(savedName);
        }

        service.register(accBoardDTO);

        return "redirect:/acc/accList";
    }
    @GetMapping("/productSingle/{acid}")
    public String readOne(@PathVariable int acid, Model model, Principal principal){
        AccBoardDTO accBoardDTO = service.readOne(acid);
        if (principal != null) {
            String userId = principal.getName();
            MemberRole userRole = memberService.findRole(userId);
            boolean isAdmin = userRole == MemberRole.ADMIN;
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("acc", accBoardDTO);
        return "acc/productSingle";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update/{acid}")
    public String UpdateGet(@PathVariable int acid,Model model){

        AccBoardDTO accBoardDTO = service.readOne(acid);
        model.addAttribute("acc",accBoardDTO);
         return "acc/productRegister";

    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/update/{acid}")
    public String UpdatePost(@Valid AccBoardDTO accBoardDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("imgFile") MultipartFile imgFile,
                             HttpSession session, @PathVariable int acid) throws IOException {

        if (imgFile != null && !imgFile.isEmpty()) {
            // 업로드 한 이미지 파일명 저장
            String imageName = imgFile.getOriginalFilename();
            String savedName = UUID.randomUUID().toString() + imageName;
            //이미지 파일 저장할 저장공간 설정
            String absolutePath = System.getProperty("user.dir");
            String path = absolutePath + "/src/main/resources/static/uploadImg";
            File file = new File(path, savedName);
            imgFile.transferTo(file);
            // 이미지 파일명을 DTO에 설정
            accBoardDTO.setImage(savedName);
        }

        service.update(accBoardDTO);
        return "redirect:/acc/productSingle/" + accBoardDTO.getAcid();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{acid}/delete")
    public String deletePost(@PathVariable int acid){
        service.delete(acid);
        return "redirect:/acc/accList";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/payment")
    public String pay(Model model,@RequestParam("acid") int acid) {
        //상품 정보 가져오기 위해서 사용
        AccBoardDTO accBoardDTO = service.readOne(acid);
        model.addAttribute("productName", accBoardDTO.getProductname());
        model.addAttribute("productPrice", accBoardDTO.getPrice());
        model.addAttribute("productImg",accBoardDTO.getImage());

        model.addAttribute("acid", acid);


        //사용자 정보 가져오기 위해 사용
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberVO userid = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
        model.addAttribute("userid", userid.getName());
        model.addAttribute("userAddress", userid.getAddress());
        model.addAttribute("userPhnum", userid.getPhnum());
        return "acc/payment";
    }

}
