package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.qaBoardVO;
import com.multicampus.hhh.dto.qaBoardDTO;
import com.multicampus.hhh.mapper.qaBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class qaBoardServiceImpl implements qaBoardService{

    private ModelMapper modelMapper;
    private qaBoardMapper qaboardMapper;

    @Override
    public void register(qaBoardDTO qaboardDTO) {
       qaBoardVO qaboardVO = modelMapper.map(qaboardDTO, qaBoardVO.class );
        qaboardMapper.insert(qaboardVO);
    }

    @Override
    public qaBoardDTO getOne(int qa_id) {
       qaBoardVO qaboardVO = qaboardMapper.selectOne(qa_id);
        qaBoardDTO qaboardDTO = modelMapper.map(qaboardVO, qaBoardDTO.class);

        return qaboardDTO;
    }

    @Override
    public void remove(int qa_id) {

        qaboardMapper.delete(qa_id);
    }

    @Override
    public void modify(qaBoardDTO qaboardDTO) {
        qaBoardVO qaboardVO = modelMapper.map(qaboardDTO, qaBoardVO.class );
        qaboardMapper.update(qaboardVO);
    }
}
