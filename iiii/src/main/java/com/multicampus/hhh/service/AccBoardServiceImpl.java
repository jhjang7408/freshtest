package com.multicampus.hhh.service;

import com.multicampus.hhh.dto.AccBoardDTO;
import com.multicampus.hhh.dto.BikeBoardDTO;
import com.multicampus.hhh.dto.KakaoPayReadyDTO;
import com.multicampus.hhh.dto.PageResponseDTO;
import com.multicampus.hhh.mapper.AccBoardMapper;
import com.multicampus.hhh.mapper.BikeBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
public class AccBoardServiceImpl implements AccBoardService{
    private final AccBoardMapper accBoardMapper;

    //카카오페이 사용하기위해 추가
    private  static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReadyDTO kakaoPayReadyDTO;

    @Override
    public List<AccBoardDTO> getAll() {

        List<AccBoardDTO> dtoList = accBoardMapper.selectAll().stream()
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void register(AccBoardDTO accBoardDTO) {
       accBoardMapper.insert(accBoardDTO);
    }

    @Override
    public AccBoardDTO readOne(int acid) {
        return null;
    }

    @Override
    public void modify(AccBoardDTO accBoardDTO) {

    }

    @Override
    public void remove(int acid) {

    }





}
