package com.multicampus.hhh.service;


import com.multicampus.hhh.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void register(OrderDTO orderDTO);
    List<OrderDTO> getOrdersByUserId(String userid);
    OrderDTO findOrderDTOByAcid(String acid);
}
