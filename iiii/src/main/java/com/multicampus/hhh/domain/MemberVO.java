package com.multicampus.hhh.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
    //컬럼 어노테이션 설정
    private String user_id;
    private int user_role_id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String address;
    private String phnum;
    private boolean social;
}
