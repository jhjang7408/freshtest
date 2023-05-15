package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.domain.QaMoonBoard;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class QaBoardMapperTest {

    @Autowired
    QaBoardMapper qaBoardMapper;

    public void createMapper(){
        QaBoard qaBoard = new QaBoard();
        qaBoard.setQaid(1234);
        qaBoard.setUserid("abc");
        qaBoard.setTitle("testTitle");
        qaBoard.setContent("tesetContestn");
        qaBoard.setRegdate(LocalDateTime.now());
        qaBoard.setImage("sda");
    }

    @Test
    public void qaMapperTest(){


        QaBoard qaBoard1 = new QaBoard();
        qaBoard1 = qaBoardMapper.findById(1234);

        log.info(qaBoard1);
    }


    @Autowired
    QaMoonBoardMapper qaMoonBoardMapper;

    @Test
    public void qq(){
        QaMoonBoard qaMoonBoard;
        List<QaMoonBoard> vv = qaMoonBoardMapper.findmoonById("test0427");
        log.info(vv+"dddddddddddddddddddddddddd");
    }



}