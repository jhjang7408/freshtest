package com.multicampus.hhh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BikeBoardVO {


    private int bikeid;

    @Length(min=4, max=20)
    private String userid;


    @Length(min=3, max=50)
    private String productname;


    private String title;


    private String image;


    private int price;
    private String info;
    private String status;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;

}
