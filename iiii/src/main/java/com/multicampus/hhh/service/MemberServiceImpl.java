package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.MemberDTO;
import com.multicampus.hhh.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberMapper memberMapper;


    @Override
    public void saveMember(MemberDTO memberDTO) {
        MemberVO member = modelMapper.map(memberDTO,MemberVO.class);

        member.addRole(MemberRole.USER);

        log.info(member);
        log.info(member.getMemberRole());

        memberMapper.save(member);
        memberMapper.saveRole(member);


    }

    @Override
    public int findMember(String id) {
        MemberVO memberVO=memberMapper.findUser(id);
        if(memberVO == null){
            return 0;
        }
        return 1;
    }

    @Override
    public MemberVO checkMember(String id, String password) {

        MemberVO memberVO = memberMapper.findUser(id);
        if (memberVO.getPassword() != password){
            return null;
        }

        return memberVO;
    }


//    @Override
//    public void modify(MemberVO memberVO) {
//
//    }
//
//
//    @Override
//    public void remove(String id) {
//
//    }
}
