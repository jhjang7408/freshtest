package com.multicampus.hhh.config.auth;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
