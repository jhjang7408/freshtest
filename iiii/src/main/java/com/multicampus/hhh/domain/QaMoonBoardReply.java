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
public class QaMoonBoardReply {

    private int moonreplyid;

    private int moonid;

    private String userid;

    private String content;

    private LocalDateTime regdate;
}
