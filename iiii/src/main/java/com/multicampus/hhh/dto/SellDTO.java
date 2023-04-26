package com.multicampus.hhh.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class SellDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    private String productName;

    @NotBlank
    private int price;

    private String content;

    private String detailInfo;
}
