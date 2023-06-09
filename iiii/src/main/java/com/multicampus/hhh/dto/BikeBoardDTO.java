package com.multicampus.hhh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BikeBoardDTO {


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
    private String nickname;
}
