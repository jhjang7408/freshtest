package com.multicampus.hhh.service;


import com.multicampus.hhh.dto.OrderDTO;
import com.multicampus.hhh.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public void register(OrderDTO orderDTO) {
        orderMapper.insert(orderDTO);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(String userid) {
        return orderMapper.findByUserId(userid);
    }

    @Override
    public OrderDTO findOrderDTOByAcid(String acid) {
        return orderMapper.findByAcid(acid);
    }


}
