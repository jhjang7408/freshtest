package com.multicampus.hhh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    @NotBlank
    @Length(min=4, max=20)
    private String user_id;

    @NotBlank
    @Length(min=4, max=20)
    private String password;
    private String name;
    private String nickname;

    private String email;
    private String address;
    private String phnum;
    private boolean social;

}
