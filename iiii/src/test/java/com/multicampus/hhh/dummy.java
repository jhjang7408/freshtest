package com.multicampus.hhh;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.mapper.AccBoardMapper;
import com.multicampus.hhh.mapper.BikeBoardMapper;
import com.multicampus.hhh.mapper.MemberMapper;
import com.multicampus.hhh.mapper.QaBoardMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class dummy {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    AccBoardMapper accBoardMapper;

    @Autowired
    BikeBoardMapper bikeBoardMapper;

    @Autowired
    QaBoardMapper qaBoardMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void create(){
        IntStream.rangeClosed(1,50).forEach(i -> {
            MemberVO member = MemberVO.builder()
                    .userid("test"+i)
                    .password("1234")
                    .name("testname"+i)
                    .nickname("testNick"+i)
                    .email("testEmail"+i+"@email.com")
                    .build();
            member.addRole(MemberRole.USER);

            String pass = member.getPassword();
            pass = bCryptPasswordEncoder.encode(pass);
            member.setPassword(pass);
            memberMapper.save(member);
            memberMapper.saveRole(member);
        });
    }

    @Test
    public void createAccBoard(){
        IntStream.rangeClosed(1,50).forEach(i->{
            AccBoardDTO board = AccBoardDTO.builder()
                    .productname("제품이름 "+i)
                    .price(i*((int)(Math.random()*10)+1))
                    .amount(50)
                    .info("제품정보 "+i)
                    .brandname("브랜드이름 "+i)
                    .build();

            accBoardMapper.insert(board);
        });
    }

    @Test
    public void cccc(){
        IntStream.rangeClosed(1,50).forEach(i->{
            BikeBoardDTO board = BikeBoardDTO.builder()
                    .userid("test"+i)
                    .productname("제품 이름"+i)
                    .title("제목 "+i)
                    .price(i*((int)(Math.random()*10)+1)*100)
                    .info("제품 정보 "+i)
                    .build();

            bikeBoardMapper.insert(board);
        });
    }

    @Test
    public void createqaBoard(){
        IntStream.rangeClosed(10,40).forEach(i->{
            QaBoard board = QaBoard.builder()
                    .userid("test"+i)
                    .title("제목 "+i)
                    .content("내용 "+i)
                    .build();

            qaBoardMapper.qaregister(board);
        });
    }
}
