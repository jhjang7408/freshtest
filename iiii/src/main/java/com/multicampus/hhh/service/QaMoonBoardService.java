package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.QaMoonBoard;
import com.multicampus.hhh.domain.QaMoonBoardReply;
import com.multicampus.hhh.mapper.QaMoonBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class QaMoonBoardService {


    private final QaMoonBoardMapper qaMoonBoardMapper;



    public List<QaMoonBoard> findmoonById(String userid) {
        log.info(userid + "dddddddddddddddddddddddddddddddddd");
        List<QaMoonBoard> qamoonlist = new ArrayList<QaMoonBoard>();
        qamoonlist = qaMoonBoardMapper.findmoonById(userid);
        log.info(qamoonlist + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return qamoonlist;
    }


    public List<QaMoonBoard> findmoonAll(){
        List<QaMoonBoard> qamoonlist = qaMoonBoardMapper.findmoonAll();
        return qamoonlist;
    }




    public QaMoonBoard findById(int moonid){
        QaMoonBoard findmoonid = qaMoonBoardMapper.findById(moonid);
        return findmoonid;
    }


    public void qamoonregister(QaMoonBoard qaMoonBoard){
        qaMoonBoardMapper.qamoonregister(qaMoonBoard);
    }


    public void qamoonmodify(QaMoonBoard qaMoonBoard){
        qaMoonBoardMapper.qamoonmodify(qaMoonBoard);
    }


    public void qamoondelete(int moonid){
        qaMoonBoardMapper.qamoondelete(moonid);
    }

    public void qamoonreplydelete(int moonid){
        qaMoonBoardMapper.qamoonreplydelete(moonid);
    }

    public void qamoonreplyregister(QaMoonBoardReply qaMoonBoardReply){
        qaMoonBoardMapper.qamoonreplyregister(qaMoonBoardReply);
    }

    public List<QaMoonBoardReply> qaMoonBoardReplyList(int moonid){
        return qaMoonBoardMapper.findreplyById(moonid);
    }

    public void qamoonreplydeleteone(int moonreplyid){
        qaMoonBoardMapper.qamoonreplydeleteone(moonreplyid);
    }

}
