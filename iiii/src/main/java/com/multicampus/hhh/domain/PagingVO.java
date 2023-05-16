package com.multicampus.hhh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PagingVO {

    private int nowPage;
    private int startPage;
    private int endPage;
    private int total;
    private int cntPerPage;
    private int lastPage;
    private int cntPage = 5;

    // 쿼리에 사용할 data
    private int start;
    private int end;

    public PagingVO(int total, int nowPage, int cntPerPage){
        setNowPage(nowPage);
        setCntPerPage(cntPerPage);
        setTotal(total);
        calcLastPage(getTotal(), getCntPerPage());
        calcStartEndPage(getNowPage(), cntPage);
        calcStartEnd(getNowPage(), getCntPerPage());
    }

    // 마지막 페이지 계산
    public void calcLastPage(int total, int cntPerPage){
        setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
    }

    // 시작, 끝 페이지 계산
    public void calcStartEndPage(int nowPage,int cntPerPage){
        setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
        if(getLastPage() < getEndPage()){
            setEndPage(getLastPage());
        }
        setStartPage(getEndPage() - cntPage +1);
        if(getStartPage() < 1 ){
            setStartPage(1);
        }
    }


    public void calcStartEnd(int nowPage, int cntPerPage){
        setEnd(nowPage * cntPerPage);
        setStart(getEnd() - cntPerPage);
    }
}
