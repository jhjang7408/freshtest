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
    List<BikeBoardDTO> findAll();

    BikeBoardDTO selectOne(int bikeid);
    void delete(int bikeid);
    void update(BikeBoardDTO bikeBoardDTO);


    List<BikeBoardVO> selectList(PageRequestDTO pageRequestDTO);
}
