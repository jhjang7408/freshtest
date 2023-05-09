package com.multicampus.hhh.service;


import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.dto.PageResponseDTO;

import java.util.List;

public interface BikeBoardReplyService {
    void register(BikeBoardReplyDTO replyDTO);

    void update(BikeBoardReplyDTO replyDTO);

    void delete(int bikereplyid);

    List<BikeBoardReplyDTO> findByBikeId(int bikeid);

    PageResponseDTO<BikeBoardReplyDTO> getListOfBoard(Long bike_id, PageRequestDTO pageRequestDTO);
}
