package com.multicampus.hhh.mapper;

import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeBoardReplyMapper {
    void insert(BikeBoardReplyDTO bikeBoardReplyDTO);
    void modify(BikeBoardReplyDTO bikeBoardReplyDTO);
    void delete(int userid);
}
