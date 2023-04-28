package com.multicampus.hhh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 장바구니 DTO ?
@Data
@Builder
@AllArgsConstructor
public class BasketDTO {
    private String bag_id;
    private String user_id;
    private Integer ac_id;
    private Integer count;
}
