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

        memberMapper.save(member);
        memberMapper.saveRole(member);
        log.info("회원가입 테스트");
        log.info(member);

    }

    @Override
    public MemberVO findMember(String id) {
        MemberVO memberVO=memberMapper.findUser(id);
        if(memberVO == null){
            return null;
        }
        return memberVO;
    }

    @Override
    public MemberVO checkMember(String id, String password) {

        MemberVO memberVO = memberMapper.findUser(id);

        if (!memberVO.getPassword().equals(password)){
            return null;
        }

        return memberVO;
    }

    @Override
    public void modifyMember(MemberVO memberVO) {

        memberMapper.updateUser(memberVO);
    }

    @Override
    public void removeMember(MemberVO memberVO) {
        log.info("회원탈퇴 유저아이디 확인");
        log.info(memberVO.getUserid());
        memberMapper.deleterole(memberVO.getUserid());
        memberMapper.deleteuser(memberVO.getUserid());
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
