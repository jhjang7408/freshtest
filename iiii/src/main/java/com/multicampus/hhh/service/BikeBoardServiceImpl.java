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

//내가 만들었던 것
//public class BikeBoardServiceImpl implements BikeBoardService{
//    @Override
//    public BikeBoardDTO readOne(Long bike_id) {
//        return null;
//    }
//
//    @Override
//    public void modify(BikeBoardDTO bikeBoardDTO) {
//
//    }
//
//    @Override
//    public void remove(Long bike_id) {
//
//    }
//
//    @Override
//    public Long register(BikeBoardDTO bikeBoardDTO) {
//        return null;
//    }
//
//    @Override
//    public PageResponseDTO<BikeBoardDTO> list(@Valid PageRequestDTO pageRequestDTO) {
//        return null;
//    }
//}
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
    public BikeBoardDTO readOne(Long bike_id) {

        return bikeBoardMapper.selectOne(bike_id);
    }

//
//    @Override
//    public void remove(Long tno) {
//
//        bikeBoardMapper.delete(tno);
//
//    }
//
//    @Override
//    public void modify(TodoDTO todoDTO) {
//
//        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
//
//        todoMapper.update(todoVO);
//
//    }

}

