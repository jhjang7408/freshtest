package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.qaBoardDTO;

public interface qaBoardService {

    public void register(qaBoardDTO qaboardDTO);


    qaBoardDTO getOne(int qa_id);

    public void remove(int qa_id);

    public void modify(qaBoardDTO qaboardDTO);
}
