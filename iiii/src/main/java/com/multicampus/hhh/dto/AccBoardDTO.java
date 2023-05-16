package com.multicampus.hhh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccBoardDTO {

    private int acid;

    private String productname;


    private String image;

    private int price;
    private int amount;
    private String info;
    private String brandname;


}
