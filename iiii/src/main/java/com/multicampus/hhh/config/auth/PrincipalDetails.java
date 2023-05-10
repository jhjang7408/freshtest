package com.multicampus.hhh.config.auth;

import com.multicampus.hhh.domain.MemberVO;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@Log4j2
public class PrincipalDetails implements UserDetails, OAuth2User {

    private MemberVO memberVO;
    private Map<String,Object> attributes;

    //일반 로그인
    public PrincipalDetails (MemberVO memberVO){
        this.memberVO = memberVO;
    }

    // 소셜 로그인
    public PrincipalDetails(MemberVO memberVO, Map<String, Object> attributes){
        this.memberVO = memberVO;
        this.attributes = attributes;
    }

    @Override   // 회원의 권한 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        if(memberVO.getMemberRole() != null){
            collect.add(new CustomGrantedAuthority("ROLE_"+memberVO.getMemberRole().name()));
        } else {
            log.error("eraseraseraseraser" + getUsername());
        }
//                new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_"+memberVO.getMemberRole().name();
//            }
//        };
        return collect;
    }

    @Override
    public String getPassword() {
        return memberVO.getPassword();
    }

    @Override
    public String getUsername() {
        return memberVO.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
