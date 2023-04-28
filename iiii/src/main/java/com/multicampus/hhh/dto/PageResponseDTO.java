package com.multicampus.hhh.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDTO<E> {

    //todo의 pageresponseDTO
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;
    private String link;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;

        this.start = this.end - 9;

        int last = (int)(Math.ceil((total/(double)size)));

        this.end = end > last ? last: end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;
    }

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
