package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
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
}
