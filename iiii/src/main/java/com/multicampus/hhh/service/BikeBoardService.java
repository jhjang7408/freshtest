package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import com.multicampus.hhh.dto.PageResponseDTO;

import java.util.List;


public interface BikeBoardService {

    List<BikeBoardDTO> getAll();
    void register(BikeBoardDTO bikeBoardDTO);
//    BikeBoardDTO readOne(Long bike_id);
//    void modify(BikeBoardDTO bikeBoardDTO);
//    void remove(Long bike_id);
//
    PageResponseDTO<BikeBoardDTO> getList(PageRequestDTO pageRequestDTO);
}
