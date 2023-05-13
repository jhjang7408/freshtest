package com.multicampus.hhh.mapper;


import com.multicampus.hhh.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderMapper {
    void insert(OrderDTO OrderDTO);
    OrderDTO selectOne(int acid);
    OrderDTO findByAcid(String acid);
    List<OrderDTO> findByUserId(String userid);

}
