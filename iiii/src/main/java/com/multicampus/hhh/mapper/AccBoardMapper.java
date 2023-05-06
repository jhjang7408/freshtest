package com.multicampus.hhh.mapper;

import com.multicampus.hhh.dto.AccBoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccBoardMapper {
    //insert,radAll,readOne,modify,delete


    void insert(AccBoardDTO accBoardDTO);
    AccBoardDTO read(Long ac_id);

    int modify(AccBoardDTO accBoardDTO);
    int delete();

}
