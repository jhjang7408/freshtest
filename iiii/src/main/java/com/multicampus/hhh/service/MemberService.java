package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;

public interface MemberService {

    void saveMember(MemberDTO memberDTO);

    MemberVO findMember(String id);

    MemberVO checkMember(String id, String password);

    void modifyMember(MemberVO memberVO);

    void removeMember(MemberVO memberVO);

    void modifyPassMember(MemberVO memberVO);

    String mailSend(String email);
    String memberEmail(String id, String email);
    void changePass(String id, String password);

    String findPassword(String userid);

//    void modify(MemberVO memberVO);
//
//    void remove(String id);
}
