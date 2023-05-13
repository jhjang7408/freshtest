package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.PagingReplyVO;
import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.domain.QaBoardReply;
import com.multicampus.hhh.mapper.PageMapper;
import com.multicampus.hhh.mapper.PageReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageReplyService {


    private final PageReplyMapper pageReplyMapper;


    public int countReplyBoard(int qaid) {
        return pageReplyMapper.countReplyBoard(qaid);
    }


    public List<QaBoardReply> selectReplyBoard(PagingReplyVO vo) {
        return pageReplyMapper.selectReplyBoard(vo);
    }

}
