package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.AccBoardDTO;

import com.multicampus.hhh.dto.PageResponseDTO;

import java.util.List;

public interface AccBoardService {
    //register 처리를 sell에서?

    List<AccBoardDTO> getAll();
    Long register(AccBoardDTO AccBoardDTO);
    AccBoardDTO readOne(Long acid);
    void modify(AccBoardDTO accBoardDTO);
    void remove(Long acid);





}
