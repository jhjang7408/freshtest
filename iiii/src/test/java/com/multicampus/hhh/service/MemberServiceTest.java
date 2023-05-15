package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void servicetest(){
        MemberDTO memberDTO = new MemberDTO().builder()
                        .userid("test1323")
                        .name("test")
                        .password("1234")
                        .name("testnm").nickname("testni").email("test@email.com")
                        .address("testadd").phnum("12345678")
                        .build();


        memberService.saveMember(memberDTO);
    }

    @Test
    @DisplayName("로그인 체크 테스트")
    public void loginCheck(){
    }

    @Test
    @DisplayName("이메일 인증 확인")
    public void emailCheck(){
        String code = memberService.mailSend("fktbzpsk@naver.com");

        log.info("인증번호 : "+code);
    }

    @Test
    @DisplayName("롤값 불러오기")
    public void findRole(){
        MemberRole memberRole = memberService.findRole("test");
        log.info("============================= "+ memberRole);
    }
}