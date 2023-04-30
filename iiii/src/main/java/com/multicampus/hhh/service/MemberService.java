package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;

public interface MemberService {

    void saveMember(MemberDTO memberDTO);

    int findMember(String id);

    MemberVO checkMember(String id, String password);

//    void modify(MemberVO memberVO);
//
//    void remove(String id);
}
