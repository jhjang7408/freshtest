package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.qaBoardVO;
import com.multicampus.hhh.dto.qaBoardDTO;
import com.multicampus.hhh.dto.qaPageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
public interface qaBoardMapper {


    void insert(qaBoardVO qaboadVO);

    List<qaBoardVO> selectAll();

    qaBoardVO selectOne(int qa_id);

    void delete(int qa_id);

    void update(qaBoardVO qaboardVO);

    List<qaBoardVO> selectList(qaPageRequestDTO pageRequestDTO);

    int getCount(qaPageRequestDTO pageRequestDTO);

}

