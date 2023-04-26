package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.MemberDTO;

public interface MemberService {

    void register(MemberDTO memberDTO);

    void modify(MemberDTO memberDTO);

    void remove(String id);
}
