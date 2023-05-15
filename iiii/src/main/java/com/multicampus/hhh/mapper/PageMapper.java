package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper {

    int countBoard();
    List<QaBoard> selectBoard(PagingVO vo);

    int countBikeBoard();
    List<BikeBoardDTO> selectBikeBoard(PagingVO vo);

    int countAccBoard();
    List<AccBoardDTO> selectAccBoard(PagingVO vo);

}
