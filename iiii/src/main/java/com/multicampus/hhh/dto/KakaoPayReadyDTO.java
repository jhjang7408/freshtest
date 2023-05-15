package com.multicampus.hhh.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayReadyDTO {

    private String tid;
    private String next_redirect_pc_url;
    private Date created_at;

}
