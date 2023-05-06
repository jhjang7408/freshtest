package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageResponseDTO;
import com.multicampus.hhh.mapper.AccBoardMapper;
import com.multicampus.hhh.mapper.BikeBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
public class AccBoardServiceImpl implements AccBoardService{
    private final AccBoardMapper accBoardMapper;

    @Override
    public List<AccBoardDTO> getAll() {

        List<AccBoardDTO> dtoList = accBoardMapper.selectAll().stream()
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public Long register(AccBoardDTO AccBoardDTO) {
        return null;
    }

    @Override
    public AccBoardDTO readOne(Long ac_id) {
        return null;
    }

    @Override
    public void modify(AccBoardDTO accBoardDTO) {

    }

    @Override
    public void remove(Long ac_id) {

    }


}
