package com.multicampus.hhh.controller;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.service.AccBoardService;
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

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/acc")
@RequiredArgsConstructor
public class AccBoardController {

    private final AccBoardService service;
    //bike와 구분 안 해도 되나?
    @Value("${com.multicampus.upload.path}")
    private String uploadPath;

    @GetMapping("/accList")
    public String list(Model model){
        log.info("악세서리 구매게시판");
        model.addAttribute("accList", service.getAll());
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
            File file = new File("C:\\upload", savedName);
            imgFile.transferTo(file);
            // 이미지 파일명을 DTO에 설정
            accBoardDTO.setImage(savedName);
        }

        service.register(accBoardDTO);

        return "redirect:/acc/accList";
    }
    @GetMapping("productSingle{acid}")
    public String readOne(@PathVariable int accid, Model model){
        AccBoardDTO accBoardDTO = service.readOne(accid);
        model.addAttribute("acc", accBoardDTO);
        return "acc/productSingle";
    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("/update/{acid}")
//    public String UpdateGet(@PathVariable int acid, Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        MemberVO userid = ((PrincipalDetails)authentication.getPrincipal()).getMemberVO();
//       //로그인 확인
//        if(userid == null){
//            log.info("로그인 하지 않았을 경우 로그인 화면으로 이동");
//            return "redirect:/member/signin";
//        }
//        AccBoardDTO accBoardDTO = service.readOne(acid);
//        //관리자만 수정가능하게
//
//    }




    @GetMapping("payment")
    public void pay(){

    }

}
