package com.multicampus.hhh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 장바구니 DTO ?
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private int bagid;
    private String userid;
    private int acid;
    private int count;

    private String pName;
    private int price;
    private String image;
}
