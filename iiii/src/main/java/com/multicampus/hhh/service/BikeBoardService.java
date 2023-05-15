package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.PageRequestDTO;
import com.multicampus.hhh.dto.PageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BikeBoardService {

    List<BikeBoardDTO> findAll();
    void register(BikeBoardDTO bikeBoardDTO);
    BikeBoardDTO readOne(int bikeid);
    void update(BikeBoardDTO bikeBoardDTO);
    void delete(int bikeid);
//

}
