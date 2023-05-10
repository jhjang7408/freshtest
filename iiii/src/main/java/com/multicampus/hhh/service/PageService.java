package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;

import java.util.List;

public interface PageService {

    public int countBoard();
    public List<QaBoard> selectBoard(PagingVO vo);
}
