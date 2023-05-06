package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.QaBoard;
import com.multicampus.hhh.domain.QaBoardReply;
import com.multicampus.hhh.mapper.QaBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)   //0428 정재욱 주석처리
public class QaBoardService {

    private final QaBoardMapper qaBoardMapper;




    public List<QaBoard> qaBoardList(){
        return qaBoardMapper.findAll();
    }




    public QaBoard findById(int qaid){
        QaBoard findqaid = qaBoardMapper.findById(qaid);
        return findqaid;
    }

    public List<QaBoardReply> qaBoardReplyList(int qaid){

        return qaBoardMapper.findreplyById(qaid);
    }





    public void qareplyregister(QaBoardReply qaBoardReply){
        qaBoardMapper.qareplyregister(qaBoardReply);
    }



    public void qaregister(QaBoard qaBoard){
        qaBoardMapper.qaregister(qaBoard);
    }


    public void qamodify(QaBoard qaBoard){
        qaBoardMapper.qamodify(qaBoard);
    }


    public void qadelete(int qaid){
        qaBoardMapper.qadelete(qaid);
    }

    public void qareplydelete(int qaid){
        qaBoardMapper.qareplydelete(qaid);
    }

    public void qareplydeleteone(int qareplyid){
        qaBoardMapper.qareplydeleteone(qareplyid);
    }



    /*public void qareplymodify(int qareplyid){
        qaBoardMapper.qareplymodify(qareplyid);
    }*/






}
