package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;

public interface MemberService {

    void saveMember(MemberDTO memberDTO);

    MemberVO findMember(String id);

    MemberVO checkMember(String id, String password);

    void modifyMember(MemberVO memberVO);

    void removeMember(MemberVO memberVO);

//    void modify(MemberVO memberVO);
//
//    void remove(String id);
}
