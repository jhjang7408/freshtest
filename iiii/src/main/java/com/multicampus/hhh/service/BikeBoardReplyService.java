package com.multicampus.hhh.service;


import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.dto.PageResponseDTO;

public interface BikeBoardReplyService {
    Long register(BikeBoardReplyDTO replyDTO);

    BikeBoardReplyDTO read(Long bike_id);

    void modify(BikeBoardReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<BikeBoardReplyDTO> getListOfBoard(Long bike_id, PageRequestDTO pageRequestDTO);
}
