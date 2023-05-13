package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.BasketDTO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;
    private final MemberMapper memberMapper;
    private final JavaMailSender javaMailSender;

    @Override
    public void saveMember(MemberDTO memberDTO) {
        MemberVO member = modelMapper.map(memberDTO,MemberVO.class);

        member.addRole(MemberRole.USER);

        memberMapper.save(member);
        memberMapper.saveRole(member);
        log.info("회원가입 테스트");
        log.info(member);

    }

    @Override
    public MemberVO findMember(String id) {
        MemberVO memberVO=memberMapper.findUser(id);
        if(memberVO == null){
            return null;
        }
        return memberVO;
    }

    @Override
    public MemberVO checkMember(String id, String password) {

        MemberVO memberVO = memberMapper.findUser(id);

        if (!memberVO.getPassword().equals(password)){
            return null;
        }

        return memberVO;
    }

    @Override
    public void modifyMember(MemberVO memberVO) {

        memberMapper.updateUser(memberVO);
    }

    @Override
    public void removeMember(MemberVO memberVO) {
        log.info("회원탈퇴 유저아이디 확인");
        log.info(memberVO.getUserid());
        memberMapper.deleterole(memberVO.getUserid());
        memberMapper.deleteuser(memberVO.getUserid());
    }

    @Override
    public void modifyPassMember(MemberVO memberVO) {
        memberMapper.updateUserPass(memberVO);

    }

    @Override
    public String mailSend(String email) {  // 인증번호 메일로 보내기

        Random random = new Random();   //난수 생성
        String key = "";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(25) + 65;

            key += (char) index;
        }
        int numIndex = random.nextInt(9999) + 1000;

        key += numIndex;
        message.setSubject("인증번호 입력을 위한 메일 전송");
        message.setText("인증번호 : " + key);
    
        javaMailSender.send(message);
        
        return key; //인증키값 반환
    }

    @Override
    public String memberEmail(String id, String email) {
        MemberVO memberVO = memberMapper.findUser(id);
        String key = "abc";
        if (memberVO != null) {
            String emailv2 = memberVO.getEmail();
            if (email.equals(emailv2)){
                key = mailSend(email);
            }
        }
        log.info("서비스 메일 검증");
        return key;
    }

    @Override
    public void changePass(String id, String password) {
        MemberVO memberVO= memberMapper.findUser(id);
        memberVO.setPassword(password);
        log.info("비밀번호 변경 테스트중"+ memberVO.getUserid());
        memberMapper.updateUserPass(memberVO);
    }

    @Override
    public String findPassword(String userid) {
        MemberVO memberVO = memberMapper.findPass(userid);
        if(memberVO.getEmail() == null){
            return "이메일이 존재하지 않습니다.";
        }
        return memberVO.getEmail();
    }

    @Override
    public void socialInsert(MemberVO memberVO) {
        memberMapper.socialInsert(memberVO);
    }

    @Override
    public MemberRole findRole(String userid) {
        int roleValue = memberMapper.findRole(userid);
        MemberRole role = MemberRole.valueOf(roleValue);
        return role;
    }

    @Override
    public List<BasketDTO> shopCart(String userid) {
        List<BasketDTO> cart = memberMapper.shopCart(userid);
        return cart;
    }

    @Override
    public void addCart(BasketDTO basketDTO) {
        //basketDTO에 userid와 acid값만 있음
        BasketDTO basketDTO2 = null;
        if (memberMapper.checkCart(basketDTO) == null) {
            log.info("장바구니 추가 서비스 테스트");
            memberMapper.addCart(basketDTO);
        } else {
            log.info("장바구니 추가 서비스 테스트 ++++");
            basketDTO2 = memberMapper.checkCart(basketDTO);
            basketDTO2.setCount(basketDTO2.getCount() + 1);
            log.info(basketDTO.getUserid(), basketDTO.getAcid() + " ======================");
            log.info(basketDTO2.getUserid(), basketDTO2.getAcid() + " ==================================");
            log.info("장바구니 추가 중복시 카운트 추가 ===================" + basketDTO.getCount());
            memberMapper.modifyCart(basketDTO2);
        }
    }

    @Override
    public void revmoveCart(int bagid) {
        memberMapper.deleteCart(bagid);
    }


    @Override
    public List<BikeBoardVO> findbike(String userid) {
        List<BikeBoardVO> bikelist = memberMapper.findbike(userid);
        return bikelist;
    }


    @Override
    public void modify(MemberVO memberVO) {

    }

    @Override
    public boolean updateCartQuantity(int id, int count) {
        memberMapper.updateCartCount(id, count);
        return true;
    }


//    @Override
//    public void remove(String id) {
//
//    }
}
