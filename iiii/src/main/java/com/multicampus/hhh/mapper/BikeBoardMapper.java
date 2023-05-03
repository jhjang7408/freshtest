package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BikeBoardMapper {
    //insert,radAll,readOne,modify,delete
    void insert(BikeBoardDTO bikeBoardDTO);
    List<BikeBoardDTO> selectAll();

    BikeBoardDTO selectOne(Long bike_id);
    void delete(String user_id);
    void update(BikeBoardVO bikeBoardVO);


    List<BikeBoardVO> selectList(PageRequestDTO pageRequestDTO);
}
