package com.multicampus.hhh.mapper;

import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccBoardMapper {
    //insert,radAll,readOne,modify,delete

    List<AccBoardDTO> selectAll();
    void insert(AccBoardDTO accBoardDTO);
    AccBoardDTO selectOne(int acid);

    int modify(AccBoardDTO accBoardDTO);
    int delete();

}
