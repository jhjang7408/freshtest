package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.AccBoardDTO;

public interface AccBoardService {
    //register 처리를 sell에서?

    AccBoardDTO readOne(Long ac_id);
    void modify(AccBoardDTO accBoardDTO);
    void remove(Long ac_id);

}
