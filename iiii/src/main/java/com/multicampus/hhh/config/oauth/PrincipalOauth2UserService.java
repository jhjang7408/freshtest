package com.multicampus.hhh.config.oauth;

import com.multicampus.hhh.config.auth.PrincipalDetails;
import com.multicampus.hhh.config.oauth.provider.GoogleUserInfo;
import com.multicampus.hhh.config.oauth.provider.Oauth2UserInfo;
import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Log4j2
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: "+ userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes: " +oAuth2User.getAttributes());

        Oauth2UserInfo oauth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("구글만 지원");
        }
        
        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oauth2UserInfo.getEmail();
//        String role = "USER";
        String name = oauth2UserInfo.getName();
        MemberVO member = memberMapper.findUser(email);

        if(member == null){
            System.out.println("소셜 로그인이 최초입니다.");
            log.info(email + " ====================================================================================");
            member = MemberVO.builder()
                    .userid(email)
                    .password(password)
                    .email(email)
                    .name(name)
                    .memberRole(MemberRole.USER)
                    .social(true)
                    .build();
            memberMapper.save(member);
            memberMapper.saveRole(member);
        } else {
            System.out.println("로그인을 한적이 있습니다.");
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }
}
