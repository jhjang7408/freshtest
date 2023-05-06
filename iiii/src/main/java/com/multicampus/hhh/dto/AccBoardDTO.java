package com.multicampus.hhh.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccBoardDTO {

    private int ac_id;

    @NotBlank
    private String product_name;

    @NotBlank
    private String image;


    @NotBlank
    private int price;
    private int amount;
    private String info;
    private String brand_name;


}
