package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper {

    int countBoard();
    List<QaBoard> selectBoard(PagingVO vo);

}
