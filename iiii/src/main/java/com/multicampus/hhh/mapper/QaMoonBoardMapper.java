package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.QaMoonBoard;

import com.multicampus.hhh.domain.QaMoonBoardReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface QaMoonBoardMapper {

    List<QaMoonBoard> findmoonById(String userid);

    QaMoonBoard findById(int moonid);

    void qamoonregister(QaMoonBoard qaMoonBoard);

    void qamoonmodify(QaMoonBoard qaMoonBoard);

    void qamoondelete(int moonid);

    void qamoonreplydelete(int moonid);

    void qamoonreplyregister(QaMoonBoardReply qaMoonBoardReply);

    List<QaMoonBoardReply> findreplyById(int moonid);

    void qamoonreplydeleteone(int moonreplyid);
}
