package com.multicampus.hhh.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Min(value = 1)
    @Positive
    private int page = 1;

    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;


    private String link;
    public int getSkip(){
        return (page -1) * 10;
    }

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            link = builder.toString();
        }
        return link;
    }





}
