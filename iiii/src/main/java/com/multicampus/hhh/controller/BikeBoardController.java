package com.multicampus.hhh.controller;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.AccBoardDTO;

import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import com.multicampus.hhh.service.BikeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
@Log4j2
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeBoardController {

    private final BikeBoardService service;

    @GetMapping("/bikeList")
    public void list(Model model){
        log.info("자전거 구매게시판");
        model.addAttribute("bikeList", service.getAll());
    }

//todo


//@RequestMapping("/bikeList")
//public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
//
//    log.info(pageRequestDTO);
//
//    if(bindingResult.hasErrors()){
//        pageRequestDTO = PageRequestDTO.builder().build();
//    }
//
//    model.addAttribute("responseDTO", service.getList(pageRequestDTO));
//}


    @GetMapping("/productRegister")
    public void registerGET() {
        log.info("GET bike register.......");
    }



    @PostMapping("/productRegister")
    public String registerPost(@Valid BikeBoardDTO bikeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, MultipartFile file) throws IOException {
//        //session에서 userid를 가져옴
//        MemberVO userid = (MemberVO) session.getAttribute("userid");
//
//        //로그인 하지 않았을 경우 로그인 페이지로 이동
//        if(userid == null){
//            log.info("로그인 하지 않았을 경우 로그인 화면으로 이동");
//            return "member/signin";
//        }

        log.info("자전거 등록");

//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            return "redirect:/sell/productRegister";
//        }

        //이미지 업로드 처리 upload 메서드를 따로 만들어야하나?
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("upload/" + fileName);
        file.transferTo(path);
        service.register(bikeBoardDTO);

        return "redirect:/bike/bikeList";
    }

    @GetMapping("/productSingle")
    public void read(){
        log.info("상세페이지");
    }




//    @GetMapping({"/read","/modify"})
//    public void read(Long bike_id, Model model) {
//
//        BikeBoardDTO bikeBoardDTO = service.readOne(bike_id);
//        log.info(bikeBoardDTO);
//
//        model.addAttribute("dto", bikeBoardDTO);
//    }
//
//    @PostMapping("/remove")
//    public String remove(Long bike_id ,RedirectAttributes redirectAttributes) {
//
//        log.info("---------remove----------");
//        log.info("bike_id"+bike_id);
//
//        service.remove(bike_id);
//
//        return "redirect:/todo/list";
//    }
//
//    @PostMapping("/modify")
//    public String modify(@Valid BikeBoardDTO bikeBoardDTO,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()) {
//
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            redirectAttributes.addAttribute("bike_id", bikeBoardDTO.getBike_id() );
//            return "redirect:/todo/modify";
//        }
//
//        log.info(bikeBoardDTO);
//        service.modify(bikeBoardDTO);
//
//        return "redirect:/todo/list";
//    }
    //내가 만들어본 부분

//    @GetMapping("/list")
//    public void list(Model model)
//    {
//        log.info("list");
//        model.addAttribute("list",service.list());
//    }
//
//    @GetMapping("/register")
//    public void register(){
//
//    }
//    @PostMapping("/register")
//    public String register(BikeBoardDTO bikeBoardDTO, RedirectAttributes rttr){
//
//        log.info("register: " + bikeBoardDTO);
//        service.register(bikeBoardDTO);
//        rttr.addFlashAttribute("result", bikeBoardDTO.get());
//
//        return "redirect:/shop/list";
//    }
//
//    @GetMapping("/read")
//    public void readOne(@RequestParam("ac_id") Long ac_id, Model model){
//
//        log.info("/read");
//        model.addAttribute("BikeBoard",service.readOne(ac_id));
//    }
//
//
//    @PostMapping("modify")
//    public String modify(BikeBoardDTO bikeBoardDTO, RedirectAttributes rttr){
//        log.info("modify: " + bikeBoardDTO);
//
//        if(service.modify(bikeBoardDTO)){
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

    //로그인 시큐리티 controller 복붙
//@GetMapping("/list")
//public void list(PageRequestDTO pageRequestDTO, Model model){
//
//    //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//
//    PageResponseDTO<BoardListAllDTO> responseDTO =
//            boardService.listWithAll(pageRequestDTO);
//
//    log.info(responseDTO);
//
//    model.addAttribute("responseDTO", responseDTO);
//}
//
//    @PreAuthorize("hasRole('user')")
//    @GetMapping("/register")
//    public void registerGET(){
//
//    }
//
//    @PostMapping("/register")
//    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//
//        log.info("board POST register.......");
//
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            return "redirect:/board/register";
//        }
//
//        log.info(boardDTO);
//
//        Long bno  = boardService.register(boardDTO);
//
//        redirectAttributes.addFlashAttribute("result", bno);
//
//        return "redirect:/board/list";
//    }
//
//
////    @GetMapping("/read")
////    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
////
////        BoardDTO boardDTO = boardService.readOne(bno);
////
////        log.info(boardDTO);
////
////        model.addAttribute("dto", boardDTO);
////
////    }
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping({"/read", "/modify"})
//    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
//
//        BoardDTO boardDTO = boardService.readOne(bno);
//
//        log.info(boardDTO);
//
//        model.addAttribute("dto", boardDTO);
//
//    }
//
//    @PreAuthorize("principal.username == #boardDTO.writer")
//    @PostMapping("/modify")
//    public String modify( @Valid BoardDTO boardDTO,
//                          BindingResult bindingResult,
//                          PageRequestDTO pageRequestDTO,
//                          RedirectAttributes redirectAttributes){
//
//        log.info("board modify post......." + boardDTO);
//
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//
//            String link = pageRequestDTO.getLink();
//
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//
//            redirectAttributes.addAttribute("bno", boardDTO.getBno());
//
//            return "redirect:/board/modify?"+link;
//        }
//
//        boardService.modify(boardDTO);
//
//        redirectAttributes.addFlashAttribute("result", "modified");
//
//        redirectAttributes.addAttribute("bno", boardDTO.getBno());
//
//        return "redirect:/board/read";
//    }
//
//
////    @PostMapping("/remove")
////    public String remove(Long bno, RedirectAttributes redirectAttributes) {
////
////        log.info("remove post.. " + bno);
////
////        boardService.remove(bno);
////
////        redirectAttributes.addFlashAttribute("result", "removed");
////
////        return "redirect:/board/list";
////
////    }
//
//    @PreAuthorize("principal.username == #boardDTO.writer")
//    @PostMapping("/remove")
//    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
//
//        Long bno  = boardDTO.getBno();
//        log.info("remove post.. " + bno);
//
//        boardService.remove(bno);
//
//        //게시물이 삭제되었다면 첨부 파일 삭제
//        log.info(boardDTO.getFileNames());
//        List<String> fileNames = boardDTO.getFileNames();
//        if(fileNames != null && fileNames.size() > 0){
//            removeFiles(fileNames);
//        }
//
//        redirectAttributes.addFlashAttribute("result", "removed");
//
//        return "redirect:/board/list";
//
//    }
//
//
//    public void removeFiles(List<String> files){
//
//        for (String fileName:files) {
//
//            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
//            String resourceName = resource.getFilename();
//
//
//            try {
//                String contentType = Files.probeContentType(resource.getFile().toPath());
//                resource.getFile().delete();
//
//                //섬네일이 존재한다면
//                if (contentType.startsWith("image")) {
//                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
//                    thumbnailFile.delete();
//                }
//
//            } catch (Exception e) {
//                log.error(e.getMessage());
//            }
//
//        }//end for
//    }
}
