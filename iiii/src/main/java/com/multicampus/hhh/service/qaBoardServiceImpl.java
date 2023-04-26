package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.qaBoardVO;
import com.multicampus.hhh.dto.qaBoardDTO;
import com.multicampus.hhh.dto.qaBoardListAllDTO;
import com.multicampus.hhh.dto.qaPageRequestDTO;
import com.multicampus.hhh.dto.qaPageResponseDTO;
import com.multicampus.hhh.mapper.qaBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class qaBoardServiceImpl implements qaBoardService{

    private ModelMapper modelMapper;
    private qaBoardMapper qaMapper;

    @Override
    public void register(qaBoardDTO qaboardDTO) {
       qaBoardVO qaboardVO = modelMapper.map(qaboardDTO, qaBoardVO.class );
        qaMapper.insert(qaboardVO);
    }

    @Override
    public qaPageResponseDTO<qaBoardDTO> getList(qaPageRequestDTO qapageRequestDTO) {
        List<qaBoardVO> voList = qaMapper.selectList(qapageRequestDTO);
        List<qaBoardDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, qaBoardDTO.class))
                .collect(Collectors.toList());

        int total = qaMapper.getCount(qapageRequestDTO);

        qaPageResponseDTO<qaBoardDTO> qapageResponseDTO = qaPageResponseDTO.<qaBoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .qapageRequestDTO(qapageRequestDTO)
                .build();

        return qapageResponseDTO;
    }


    @Override
    public qaBoardDTO getOne(int qa_id) {
       qaBoardVO qaboardVO = qaMapper.selectOne(qa_id);
        qaBoardDTO qaboardDTO = modelMapper.map(qaboardVO, qaBoardDTO.class);

        return qaboardDTO;
    }

    @Override
    public void remove(int qa_id) {

        qaMapper.delete(qa_id);
    }

    @Override
    public void modify(qaBoardDTO qaboardDTO) {
        qaBoardVO qaboardVO = modelMapper.map(qaboardDTO, qaBoardVO.class );
        qaMapper.update(qaboardVO);
    }



}
