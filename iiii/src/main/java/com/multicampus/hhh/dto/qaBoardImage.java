package com.multicampus.hhh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class qaBoardImage {

    private String uuid;

    private String fileName;

    private int ord;
}
