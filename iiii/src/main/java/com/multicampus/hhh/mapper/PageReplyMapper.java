package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.PagingReplyVO;
import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.domain.QaBoardReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PageReplyMapper {

    int countReplyBoard(int qaid);
    List<QaBoardReply> selectReplyBoard(PagingReplyVO vo);








}
