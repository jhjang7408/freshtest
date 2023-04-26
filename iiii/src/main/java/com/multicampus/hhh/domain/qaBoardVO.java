package com.multicampus.hhh.domain;

import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class qaBoardVO {

    private Integer qa_id;
    private String user_id;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private List<String> fileNames;
}
