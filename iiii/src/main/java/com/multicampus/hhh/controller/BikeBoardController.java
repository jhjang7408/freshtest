package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.AccBoardDTO;

import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import com.multicampus.hhh.service.BikeBoardReplyService;
import com.multicampus.hhh.service.BikeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeBoardController {

    private final BikeBoardService service;
    private final BikeBoardReplyService replyService;
    @Value("${com.multicampus.upload.path}")
    private String uploadPath;

    @GetMapping("/bikeList")
    public String list(Model model){
        log.info("자전거 구매게시판");
        model.addAttribute("bikeList", service.findAll());
        return "bike/bikeList";
    }


    @GetMapping("/productRegister")
    public String registerGET(Model model, HttpSession session) {
        //session에서 userid를 가져옴
        MemberVO userid = (MemberVO) session.getAttribute("loginId");

        //로그인 하지 않았을 경우 로그인 페이지로 이동
        /*if(userid == null){
            log.info("로그인 하지 않았을 경우 로그인 화면으로 이동");
            return "redirect:/member/signin";
        }*/
        model.addAttribute("userid", userid.getUserid());
        log.info("로그인 확인되어 등록페이지로 이동");
        return "bike/productRegister";
    }



    @PostMapping("/productRegister")
    public String registerPost(@Valid BikeBoardDTO bikeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, @RequestParam("imgFile") MultipartFile imgFile) throws IOException {


        log.info("자전거 등록");

//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            return "redirect:/bike/productRegister";
//        }
        if (imgFile != null && !imgFile.isEmpty()) {
            // 업로드 한 이미지 파일명 저장
            String imageName = imgFile.getOriginalFilename();
            String savedName = UUID.randomUUID().toString() + imageName;
            //이미지 파일 저장할 저장공간 설정
            File file = new File("C:\\upload", savedName);
            imgFile.transferTo(file);
            // 이미지 파일명을 DTO에 설정
            bikeBoardDTO.setImage(savedName);
        }

        service.register(bikeBoardDTO);

        return "redirect:/bike/bikeList";
    }

    @GetMapping("/productSingle/{bikeid}")
    public String readOne(@PathVariable int bikeid, Model model) {
        BikeBoardDTO bikeBoardDTO = service.readOne(bikeid);
        //아래 2줄 댓글 보기위해 추가
        List<BikeBoardReplyDTO> replies = replyService.findByBikeId(bikeid);
        model.addAttribute("replies", replies);
        model.addAttribute("bike", bikeBoardDTO);
        return "bike/productSingle";
    }

    @GetMapping("/update/{bikeid}")
    public String UpdateGet(@PathVariable int bikeid,Model model,HttpSession session){
        //session에서 userid를 가져옴
        MemberVO userid = (MemberVO) session.getAttribute("loginId");

        //로그인 하지 않았을 경우 로그인 페이지로 이동
        if(userid == null){
            log.info("로그인 하지 않았을 경우 로그인 화면으로 이동");
            return "redirect:/member/signin";
        }
        //
        BikeBoardDTO bikeBoardDTO = service.readOne(bikeid);
        if(userid.getUserid().equals(bikeBoardDTO.getUserid())){
            model.addAttribute("bike",bikeBoardDTO);
            return "bike/productRegister";
        }else{
            //아이디 불일치 시 오류
            model.addAttribute("errorMsg", "글 작성자만 수정할 수 있습니다.");
            return "redirect:/bike/productSingle/" + bikeid;
        }
    }

    @PostMapping("/update/{bikeid}")
    public String UpdatePost(@Valid BikeBoardDTO bikeBoardDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("imgFile") MultipartFile imgFile,
                             HttpSession session, @PathVariable int bikeid) throws IOException{
        //세션에서 아이디 가져옴
        MemberVO userid = (MemberVO) session.getAttribute("loginId");
        BikeBoardDTO bikeidDTO = service.readOne(bikeBoardDTO.getBikeid());
        String writer = bikeidDTO.getUserid();

        if (userid != null && userid.getUserid().equals(writer)) {


            // 이미지 파일 처리
            if (imgFile != null && !imgFile.isEmpty()) {
                String imageName = imgFile.getOriginalFilename();
                String savedName = UUID.randomUUID().toString() + imageName;
                File file = new File("C:\\upload", savedName);
                imgFile.transferTo(file);
                bikeBoardDTO.setImage(savedName);
            }

            service.update(bikeBoardDTO);
            return "redirect:/bike/productSingle/" + bikeBoardDTO.getBikeid();
        } else {
            // 일치하지 않는 경우 오류 메시지를 표시하고 게시글 상세 페이지로 이동
            redirectAttributes.addFlashAttribute("errorMsg", "글 작성자만 수정할 수 있습니다.");
            return "redirect:/bike/productSingle/" + bikeBoardDTO.getBikeid();
        }
    }

    @PostMapping("/{bikeid}/delete")
    public String deletePost(@PathVariable int bikeid,HttpSession session,Model model){
        //로그인한 id
        MemberVO userid = (MemberVO) session.getAttribute("loginId");
        BikeBoardDTO bikeBoardDTO = service.readOne(bikeid);

        //로그인 하지 않았을 경우
        if (userid == null) {
            return "redirect:/member/signin";
        }
        //작성자 id
        String writer = bikeBoardDTO.getUserid();
        model.addAttribute("writer", writer);

        if(userid.getUserid().equals(writer)){
            service.delete(bikeid);
            log.info("삭제진행");
            return "redirect:/bike/bikeList";
        }
        else{
            return "redirect:/bike" + bikeid;
        }
    }





}