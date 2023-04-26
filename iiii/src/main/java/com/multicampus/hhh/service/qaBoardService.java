package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.qaBoardDTO;
import com.multicampus.hhh.dto.qaBoardListAllDTO;
import com.multicampus.hhh.dto.qaPageRequestDTO;
import com.multicampus.hhh.dto.qaPageResponseDTO;

public interface qaBoardService {

    public void register(qaBoardDTO qaboardDTO);

    qaPageResponseDTO<qaBoardDTO> getList(qaPageRequestDTO qapageRequestDTO);



    qaBoardDTO getOne(int qa_id);

    public void remove(int qa_id);

    public void modify(qaBoardDTO qaboardDTO);
}
