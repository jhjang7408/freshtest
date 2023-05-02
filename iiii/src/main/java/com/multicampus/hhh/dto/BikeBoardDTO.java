package com.multicampus.hhh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private int price;
    private String info;
    private String status;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;

}
