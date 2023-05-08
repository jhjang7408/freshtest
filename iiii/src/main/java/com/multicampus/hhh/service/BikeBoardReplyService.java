package com.multicampus.hhh.service;


import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.dto.PageResponseDTO;

public interface BikeBoardReplyService {
    void register(BikeBoardReplyDTO replyDTO);

    void modify(BikeBoardReplyDTO replyDTO);

    void delete(String userid);

    PageResponseDTO<BikeBoardReplyDTO> getListOfBoard(Long bike_id, PageRequestDTO pageRequestDTO);
}
