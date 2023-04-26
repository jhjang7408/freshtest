package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void insert(MemberVO memberVO);
    void delete(String id);
    void update(MemberVO memberVO);

}
