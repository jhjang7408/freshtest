package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.MemberVO;
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

        log.info(memberVO);
    }

}