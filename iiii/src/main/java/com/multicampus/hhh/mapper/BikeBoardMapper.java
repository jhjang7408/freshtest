package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeBoardMapper {
    //insert,radAll,readOne,modify,delete
    void insert(BikeBoardVO bikeBoardDTO);
    BikeBoardDTO read(Long ac_id);
    int delete(String user_id);
    int modify(BikeBoardDTO bikeBoardDTO);
}
