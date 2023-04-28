package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
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
                        .user_id("test1323")
                        .name("test")
                        .password("1234")
                        .name("testnm").nickname("testni").email("test@email.com")
                        .address("testadd").phnum("12345678")
                        .build();


        memberService.saveMember(memberDTO);
    }

}