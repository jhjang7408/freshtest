package com.multicampus.hhh.service;



import com.multicampus.hhh.dto.BikeBoardReplyDTO;
import com.multicampus.hhh.dto.PageResponseDTO;

import com.multicampus.hhh.mapper.BikeBoardReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BikeBoardReplyServiceImpl implements BikeBoardReplyService {

    private final BikeBoardReplyMapper bikeBoardReplyMapper;

    @Override
    public void register(BikeBoardReplyDTO replyDTO) {
        bikeBoardReplyMapper.insert(replyDTO);
    }


    @Override
    public void modify(BikeBoardReplyDTO replyDTO) {
        bikeBoardReplyMapper.modify(replyDTO);
    }


    @Override
    public void delete(String userid) {
        bikeBoardReplyMapper.delete(userid);
    }

    @Override
    public PageResponseDTO<BikeBoardReplyDTO> getListOfBoard(Long bike_id, PageRequestDTO pageRequestDTO) {
        return null;
    }

}
