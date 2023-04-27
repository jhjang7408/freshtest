package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MemberMapper {

    void save(MemberVO memberVO);   //회원등록
    void saveRole(MemberVO memberVO);
    public MemberVO findUser(String user_id);    //회원조회

    public void delete(String id);
    public void update(MemberDTO memberDTO);

}
