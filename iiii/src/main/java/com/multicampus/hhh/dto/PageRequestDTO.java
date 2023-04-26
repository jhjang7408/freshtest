package com.multicampus.hhh.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 10;

    @Builder.Default
    private int size = 10;

    private String type;

    private String keyword;

}
