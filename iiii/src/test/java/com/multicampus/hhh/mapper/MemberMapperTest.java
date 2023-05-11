package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void findTest(){
        MemberVO memberVO = new MemberVO();
        memberVO = memberMapper.findUser("test");

        log.info("테스트값");
        log.info(memberVO);
    }

    @Test
    public void find2Test(){
        MemberVO memberVO = new MemberVO();
        memberVO = memberMapper.findjoinUser("tat");
        log.info("테스트");
        log.info(memberVO);
    }

    @Test
    public void findtestee(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO = memberMapper.findtest("logintest");
        log.info("테스트");
        log.info(memberDTO);
    }

    @Test
    public void roleFind(){
        MemberVO memberVO = new MemberVO();
        memberVO = memberMapper.findjoinUser("tat");
        memberVO.builder().userid("tat").memberRole(MemberRole.USER).build();
        log.info(memberVO);
    }

    @Test
    public void modTest(){
        memberMapper.updateUser(memberMapper.findUser("test"));
    }


    @Test
    public void RoleFind(){
        log.info("==============================" + memberMapper.findRole("jhjang7408@naver.com"));
        int aa = memberMapper.findRole("jhjang7408@naver.com");
        log.info(aa + "<<<<<<<<<<<<<<<<<<");


    }





}