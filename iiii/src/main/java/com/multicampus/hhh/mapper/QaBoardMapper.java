package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.QaBoard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QaBoardMapper {


    List<QaBoard> findAll();

    QaBoard findById(int qaid);

    void qaregister(QaBoard qaBoard);

    void qamodify(QaBoard qaBoard);

    void qadelete(int qaid);





}
