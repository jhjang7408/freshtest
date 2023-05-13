package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.PagingMoonVO;
import com.multicampus.hhh.domain.PagingVO;
import com.multicampus.hhh.domain.QaMoonBoard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PageMoonMapper {

    int countMoonBoard(String  userid);

    List<QaMoonBoard> selectMoonBoard(PagingMoonVO vo);

    int countMoonBoardAll();

    List<QaMoonBoard> selectMoonBoardAll(PagingVO vvo);
}
