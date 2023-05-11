package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.AccBoardDTO;

import com.multicampus.hhh.dto.PageResponseDTO;

import java.util.List;

public interface AccBoardService {
    //register 처리를 sell에서?

    List<AccBoardDTO> getAll();
    void register(AccBoardDTO AccBoardDTO);
    AccBoardDTO readOne(int acid);
    void update(AccBoardDTO accBoardDTO);
    void delete(int acid);





}
