package com.multicampus.hhh.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements Oauth2UserInfo{

    private Map<String,Object> attributes;

    public KakaoUserInfo(Map<String,Object> attributes){
        this.attributes = attributes;

    }

    @Override
    public String getProviderId() {
        if (attributes == null) {
            throw new IllegalStateException("attributes map is null");
        }
        return (String) attributes.get("id");
    }


    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)((Map) attributes.get("profile")).get("nickname");
    }
}
