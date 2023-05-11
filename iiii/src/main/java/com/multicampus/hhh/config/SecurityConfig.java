package com.multicampus.hhh.config;

import com.multicampus.hhh.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/mypage/**").authenticated()  // 인증된 회원만 접속가능
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/member/signin")    //일반 로그인
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureHandler(new CustomLoginFailHandler())
                .and()
                .oauth2Login().loginPage("/member/signin")  //소셜 로그인
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .defaultSuccessUrl("/social")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDenieHandler())    //접근 권한 설정
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())    //인증 설정
                .and()
                .logout()   //로그아웃
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                .and()
                .rememberMe()   //자동 로그인
                .key("remember-me");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**");
//    }
}
