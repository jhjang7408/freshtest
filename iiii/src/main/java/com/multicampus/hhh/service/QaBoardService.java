package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.mapper.QaBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QaBoardService {

    private final QaBoardMapper qaBoardMapper;

    public List<QaBoard> qaBoardList(){
        return qaBoardMapper.findAll();
    }

    public QaBoard findById(int qaid){
        return qaBoardMapper.findById(qaid);
    }

}