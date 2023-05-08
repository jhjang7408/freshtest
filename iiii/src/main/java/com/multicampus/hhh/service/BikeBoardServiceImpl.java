package com.multicampus.hhh.service;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import com.multicampus.hhh.dto.PageResponseDTO;
import com.multicampus.hhh.mapper.BikeBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BikeBoardServiceImpl implements BikeBoardService{

    private final BikeBoardMapper bikeBoardMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(BikeBoardDTO bikeBoardDTO) {

//        log.info("serviceRegister1");
//        BikeBoardVO bikeBoardVO = modelMapper.map(bikeBoardDTO, BikeBoardVO.class);
//        log.info("serviceRegister2");
        bikeBoardMapper.insert(bikeBoardDTO);
    }

    @Override
	public List<BikeBoardDTO> findAll() {

//		List<BikeBoardDTO> dtoList = bikeBoardMapper.findAll().stream()
//				.collect(Collectors.toList());
//
//		return dtoList;
        return bikeBoardMapper.findAll();
	}
    @Override
    public PageResponseDTO<BikeBoardDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BikeBoardVO> voList = bikeBoardMapper.selectList(pageRequestDTO);
        List<BikeBoardDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, BikeBoardDTO.class))
                .collect(Collectors.toList());


        PageResponseDTO<BikeBoardDTO> pageResponseDTO = PageResponseDTO.<BikeBoardDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

//
    @Override
    public BikeBoardDTO readOne(int bikeid) {

        return bikeBoardMapper.selectOne(bikeid);
    }
    @Override
    public void update(BikeBoardDTO bikeBoardDTO) {
        bikeBoardMapper.update(bikeBoardDTO);
    }

    @Override
    public void delete(int bikeid) {

        bikeBoardMapper.delete(bikeid);

    }



}

