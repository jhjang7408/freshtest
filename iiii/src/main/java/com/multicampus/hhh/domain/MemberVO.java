package com.multicampus.hhh.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class MemberVO {
    //컬럼 어노테이션 설정

    private String userid;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String address;
    private String phnum;
    private boolean social;




    private MemberRole memberRole = MemberRole.USER;

    public void addRole(MemberRole memberRole) {
        if (this.memberRole == null) {
            this.memberRole = memberRole;
        }
    }

}
