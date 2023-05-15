package com.multicampus.hhh.mapper;

import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BikeBoardReplyMapper {
    void insert(BikeBoardReplyDTO bikeBoardReplyDTO);
    void modify(BikeBoardReplyDTO bikeBoardReplyDTO);
    void delete(int bikereplyid);
    //게시물 삭제 시 댓글 존재하면 외래키 제약조건에 걸려서 먼저 게시물의 댓글 모두삭제하는 delete
    void deleteByBikeId(int bikeid);
    List<BikeBoardReplyDTO> findByBikeId(int bikeid);

}
