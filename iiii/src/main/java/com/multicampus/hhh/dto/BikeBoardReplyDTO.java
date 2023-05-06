package com.multicampus.hhh.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BikeBoardReplyDTO {

    private int bikereplyid;


    @Length(min=4, max=20)
    private String userid;
    private int bikeid;
    private String content;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;
}
