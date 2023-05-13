package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingMoonVO;
import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaMoonBoard;
import com.multicampus.hhh.mapper.PageMoonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageMoonService {

    private final PageMoonMapper pageMoonMapper;

    public int countMoonBoard(String userid){
        return pageMoonMapper.countMoonBoard(userid);
    }

    public List<QaMoonBoard> selectMoonBoard(PagingMoonVO vo){
        return pageMoonMapper.selectMoonBoard(vo);
    }

    public int countMoonBoardAll(){
        return pageMoonMapper.countMoonBoardAll();
    }

    public List<QaMoonBoard> selectMoonBoardAll(PagingVO vvo){
        return pageMoonMapper.selectMoonBoardAll(vvo);
    }

}
