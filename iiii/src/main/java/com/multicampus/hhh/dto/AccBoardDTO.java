package com.multicampus.hhh.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AccBoardDTO {

    private int acid;

    private String productname;


    private String image;

    private int price;
    private int amount;
    private String info;
    private String brandname;


}
