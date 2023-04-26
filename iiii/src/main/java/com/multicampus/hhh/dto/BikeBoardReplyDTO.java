package com.multicampus.hhh.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BikeBoardReplyDTO {

    private int bike_reply_id;

    @NotBlank
    @Length(min=4, max=20)
    private String user_id;
    private int bike_id;
    private String content;
    private LocalDateTime regdate;
}
