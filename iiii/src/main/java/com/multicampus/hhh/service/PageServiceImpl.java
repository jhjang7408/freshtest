package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.mapper.PageMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PageServiceImpl implements PageService{

    @Autowired
    private PageMapper pageMapper;

    @Override
    public int countBoard() {
        return pageMapper.countBoard();
    }

    @Override
    public List<QaBoard> selectBoard(PagingVO vo) {
        return pageMapper.selectBoard(vo);
    }

    @Override
    public int countBikeBoard() {
        return pageMapper.countBikeBoard();
    }

    @Override
    public List<BikeBoardDTO> selectBikeBoard(PagingVO vo) {
        return pageMapper.selectBikeBoard(vo);
    }

    @Override
    public int countAccBoard() {
        return pageMapper.countAccBoard();
    }

    @Override
    public List<AccBoardDTO> selectAccBoard(PagingVO vo) {
        return pageMapper.selectAccBoard(vo);
    }
}
