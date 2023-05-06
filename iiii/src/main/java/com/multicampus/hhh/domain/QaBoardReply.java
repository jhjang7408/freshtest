package com.multicampus.hhh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QaBoardReply {

    private int qareplyid;

    private int qaid;

    private String userid;

    private String content;

    private LocalDateTime regdate;



}
