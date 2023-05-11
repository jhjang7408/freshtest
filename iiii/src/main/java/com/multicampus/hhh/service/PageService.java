package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;

import java.util.List;

public interface PageService {

    public int countBoard();
    public List<QaBoard> selectBoard(PagingVO vo);

    int countBikeBoard();
    List<BikeBoardDTO> selectBikeBoard(PagingVO vo);

    int countAccBoard();
    List<AccBoardDTO> selectAccBoard(PagingVO vo);
}
