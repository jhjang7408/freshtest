package com.multicampus.hhh.mapper;

import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BikeBoardReplyMapper {
    void insert(BikeBoardReplyDTO bikeBoardReplyDTO);
    void modify(BikeBoardReplyDTO bikeBoardReplyDTO);
    void delete(int bikereplyid);
    List<BikeBoardReplyDTO> findByBikeId(int bikeid);

}
