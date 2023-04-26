package com.multicampus.hhh.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class BikeBoardDTO {


    private int bike_id;
    @NotBlank
    @Length(min=4, max=20)
    private String user_id;

    @NotBlank
    @Length(min=3, max=50)
    private String product_name;

    @NotBlank
    private String title;

    @NotBlank
    private String image;

    @NotBlank
    private int price;
    private String info;
    private String status;
    private LocalDateTime regdate;

}
