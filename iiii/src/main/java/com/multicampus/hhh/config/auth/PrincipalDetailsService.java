package com.multicampus.hhh.config.auth;

import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.mapper.MemberMapper;
import com.multicampus.hhh.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = memberMapper.findUser(username);
        log.info("PrincipalDetailsService 클래스 유저아이디 " + username);
        log.info("PrincipalDetailsService 클래스 유저권한" + memberService.findRole(username));
        if(member != null) {
            return new PrincipalDetails(member, memberService);
        }
        return null;
    }
}
