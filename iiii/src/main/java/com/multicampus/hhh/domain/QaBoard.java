package com.multicampus.hhh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QaBoard {

    private int qaid;

    private String userid;

    private String title;

    private String content;

    private LocalDateTime regdate;

    private String image;


    public QaBoard(LocalDateTime regdate){
        this.regdate=LocalDateTime.now();
    }

}
