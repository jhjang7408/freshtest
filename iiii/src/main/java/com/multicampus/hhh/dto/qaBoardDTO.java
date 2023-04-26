package com.multicampus.hhh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class qaBoardDTO {

    private Integer qa_id;

    @NotEmpty
    private String user_id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private LocalDateTime regDate;

    private List<String> fileNames;

}
